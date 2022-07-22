package com.loud9ja.loud9ja.ui.polls

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentPollDetailsBinding
import com.loud9ja.loud9ja.domain.network.api.polls.PollResponseItem
import com.loud9ja.loud9ja.domain.network.api.polls.VoteRequest
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.Constants
import com.loud9ja.loud9ja.utils.UIstate
import com.loud9ja.loud9ja.utils.VoteListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PollDetailsFragment : BindingFragment<FragmentPollDetailsBinding>() {
    private val viewModel: PollsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val poll = args?.get("poll") as PollResponseItem
        Log.d("POST ================>", "onViewCreated: $poll")
        binding.auther.text = poll.user?.name
        Glide.with(requireContext()).load("${Constants.IMAGE_PATH}${poll.user?.profilePicture}")
            .error(R.drawable.profile_image).into(binding.pollCreatorProfileImage)
        val voteView = binding.voteView
        val voteData = LinkedHashMap<String, Int>()
        poll.options?.forEach {
            voteData[it?.option.toString()] = 20 * poll.options.indexOf(it)
        }
        voteView.initVote(voteData);
        voteView.setAnimationRate(600);

        voteView.setVoteListener(object : VoteListener {
            override fun onItemClick(view: View?, index: Int, status: Boolean): Boolean {
                if (!status) {
                    //
                } else {
                    voteView.notifyUpdateChildren(view, true)
                }
                viewModel.vote(
                    VoteRequest(
                        poll.id!!,
                        poll.options!![index]!!.id!!
                    )
                )
                observeVote()
                return true
            }
        })
    }

    private fun observeVote() {
        viewModel.voteResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is UIstate.Success -> {
                    Toast.makeText(requireContext(), "Voted successfully", Toast.LENGTH_LONG).show()

                }
                is UIstate.Loading -> {}
                is UIstate.Error -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPollDetailsBinding::inflate

}