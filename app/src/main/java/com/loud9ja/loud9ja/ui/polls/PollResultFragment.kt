package com.loud9ja.loud9ja.ui.polls

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentDiscussionDetailBinding
import com.loud9ja.loud9ja.databinding.FragmentPollDetailsBinding
import com.loud9ja.loud9ja.databinding.FragmentPollResultBinding
import com.loud9ja.loud9ja.domain.network.api.polls.Poll
import com.loud9ja.loud9ja.utils.BindingFragment

class PollResultFragment : BindingFragment<FragmentPollResultBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val post = args?.get("poll") as Poll
    }


    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPollResultBinding::inflate
}