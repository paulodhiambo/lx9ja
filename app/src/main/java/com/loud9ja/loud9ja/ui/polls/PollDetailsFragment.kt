package com.loud9ja.loud9ja.ui.polls

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentDiscussionDetailBinding
import com.loud9ja.loud9ja.databinding.FragmentPollDetailsBinding
import com.loud9ja.loud9ja.domain.network.api.polls.Poll
import com.loud9ja.loud9ja.utils.BindingFragment

class PollDetailsFragment : BindingFragment<FragmentDiscussionDetailBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val post = args?.get("poll") as Poll
        Log.d("POST ================>", "onViewCreated: $post")
    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPollDetailsBinding::inflate

}