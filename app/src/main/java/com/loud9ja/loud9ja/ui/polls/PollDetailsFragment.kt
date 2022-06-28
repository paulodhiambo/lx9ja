package com.loud9ja.loud9ja.ui.polls

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentPollDetailsBinding
import com.loud9ja.loud9ja.domain.network.api.polls.Poll
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.VoteListener

class PollDetailsFragment : BindingFragment<FragmentPollDetailsBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val poll = args?.get("poll") as Poll
        Log.d("POST ================>", "onViewCreated: $poll")
        val voteView = binding.voteView
        val voteData = LinkedHashMap<String, Int>()
        poll.options.forEach {
            voteData[it.option] = 20 * poll.options.indexOf(it)
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
                return true
            }
        })
    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPollDetailsBinding::inflate

}