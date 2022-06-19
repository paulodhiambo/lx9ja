package com.loud9ja.loud9ja.ui.polls

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentPollDetailBinding
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PollDetailFragment : BindingFragment<FragmentPollDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPollDetailBinding::inflate

}