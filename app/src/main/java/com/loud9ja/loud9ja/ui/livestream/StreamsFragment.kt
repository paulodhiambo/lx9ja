package com.loud9ja.loud9ja.ui.livestream

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.common.base.Strings.isNullOrEmpty
import com.google.firebase.database.*
import com.loud9ja.loud9ja.BuildConfig.AUTH_TOKEN
import com.loud9ja.loud9ja.BuildConfig.AUTH_URL
import com.loud9ja.loud9ja.databinding.FragmentStreamsBinding
import com.loud9ja.loud9ja.domain.firebase.stream.LiveStream
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.video.JoinActivity
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import org.json.JSONObject


@AndroidEntryPoint
class StreamsFragment : BindingFragment<FragmentStreamsBinding>() {
    private val streamsAdapter: StreamsAdapter by lazy { StreamsAdapter() }
    private lateinit var databaseReference: DatabaseReference
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentStreamsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseReference = FirebaseDatabase.getInstance().reference
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val posts: MutableList<LiveStream> = ArrayList()
                dataSnapshot.children.forEach {
                    it.getValue(LiveStream::class.java)?.let { it1 -> posts.add(it1) }
                }
                streamsAdapter.items = posts
                binding.recentPostShimmerLayout.stopShimmer()
                binding.recentPostShimmerLayout.visibility = View.GONE
                binding.trendingRecycler.visibility = View.VISIBLE
                binding.trendingRecycler.apply {
                    hasFixedSize()
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = streamsAdapter
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Error=====>", "loadPost:onCancelled", databaseError.toException())
            }
        }
        databaseReference.child("streams")
            .addValueEventListener(postListener)

        streamsAdapter.listener = { _, item, _ ->
            getToken(item.streamId)
        }

    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


    private fun getToken(meetingId: String?) {
        if (!isNetworkAvailable()) {
            return
        }
        if (!isNullOrEmpty(AUTH_TOKEN) && !isNullOrEmpty(AUTH_URL)) {
            Toast.makeText(
                requireContext(),
                "Please Provide only one - either auth_token or auth_url",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (!isNullOrEmpty(AUTH_TOKEN)) {
            if (meetingId == null) {
                //createMeeting(AUTH_TOKEN)
            } else {
                joinMeeting(AUTH_TOKEN, meetingId)
            }
            return
        }
        if (!isNullOrEmpty(AUTH_URL)) {
            AndroidNetworking.get("$AUTH_URL/get-token")
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            val token = response.getString("token")
                            if (meetingId == null) {
                                //createMeeting(token)
                            } else {
                                joinMeeting(token, meetingId)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onError(anError: ANError) {
                        anError.printStackTrace()
                        Toast.makeText(
                            requireContext(),
                            anError.errorDetail, Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            return
        }
        Toast.makeText(
            requireContext(),
            "Please Provide auth_token or auth_url", Toast.LENGTH_SHORT
        ).show()
    }

    private fun joinMeeting(token: String, meetingId: String) {
        AndroidNetworking.post("https://api.videosdk.live/v1/meetings/$meetingId")
            .addHeaders("Authorization", token)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val intent = Intent(requireContext(), JoinActivity::class.java)
                    intent.putExtra("token", token)
                    intent.putExtra("meetingId", meetingId)
                    intent.putExtra("title", "")
                    startActivity(intent)
                }

                override fun onError(anError: ANError) {
                    anError.printStackTrace()
                    Toast.makeText(
                        requireContext(), anError.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }


}