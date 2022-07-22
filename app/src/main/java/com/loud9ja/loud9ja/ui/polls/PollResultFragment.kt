package com.loud9ja.loud9ja.ui.polls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentPollResultBinding
import com.loud9ja.loud9ja.domain.network.api.polls.PollResponseItem
import com.loud9ja.loud9ja.utils.BindingFragment

class PollResultFragment : BindingFragment<FragmentPollResultBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val post = args?.get("poll") as PollResponseItem
    }


    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPollResultBinding::inflate
}