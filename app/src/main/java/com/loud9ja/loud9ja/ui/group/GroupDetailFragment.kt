package com.loud9ja.loud9ja.ui.group

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentGroupDetailBinding
import com.loud9ja.loud9ja.domain.firebase.group.GroupPost
import com.loud9ja.loud9ja.domain.network.api.groups.Group
import com.loud9ja.loud9ja.utils.AuthPreference
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.Constants.IMAGE_PATH
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.random.Random


@AndroidEntryPoint
class GroupDetailFragment : BindingFragment<FragmentGroupDetailBinding>() {
    private lateinit var database: DatabaseReference
    private val commentAdapter: GroupPostDetailRecyclerViewAdapter by lazy {
        GroupPostDetailRecyclerViewAdapter()
    }
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentGroupDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
        val user = AuthPreference(requireContext()).getAuthDetails()
        val args = this.arguments
        val group = args?.get("group") as Group
        Glide.with(requireContext()).load("${IMAGE_PATH}${group.media}").error(R.drawable.banner1)
            .into(binding.profileImage)
        Glide.with(requireContext()).load("${IMAGE_PATH}${user?.image}").error(R.drawable.profile_image)
            .into(binding.ghImage)
        binding.textViewPostAuthor.text = group.name
        binding.textViewPostContent.text = group.description

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val posts: MutableList<GroupPost> = ArrayList()
                dataSnapshot.children.forEach {
                    it.getValue(GroupPost::class.java)?.let { it1 -> posts.add(it1) }
                }
                commentAdapter.items = posts
                binding.postCommentRecycler.apply {
                    hasFixedSize()
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = commentAdapter
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Error=====>", "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.child("group").child(group.id.toString()).child("posts")
            .addValueEventListener(postListener)
        binding.createButton.setOnClickListener {
            val body = binding.post.text.toString().trim()
            if (body.isBlank()) {
                Toast.makeText(requireContext(), "Please input post", Toast.LENGTH_LONG).show()
            } else {
                val date = Calendar.getInstance().time
                writeNewPost(
                    "${user?.image}",
                    "${user?.image}",
                    "$date",
                    "${user?.userName}",
                    group.name,
                    body,
                    group.id
                )
                binding.post.setText("")
                Toast.makeText(requireContext(), "Post added successfully", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun writeNewPost(
        image: String,
        userImage: String,
        date: String,
        username: String,
        title: String,
        body: String,
        groupId: Int
    ) {
        val post = GroupPost(image, userImage, date, username, title, body)
        val postId = Random(999999).nextInt()
        database.child("group").child(groupId.toString()).child("posts").child("${postId}${date}")
            .setValue(post)
    }


}