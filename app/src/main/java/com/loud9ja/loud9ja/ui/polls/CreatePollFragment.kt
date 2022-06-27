package com.loud9ja.loud9ja.ui.polls

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentCreatePollBinding
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePollFragment : BindingFragment<FragmentCreatePollBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentCreatePollBinding::inflate

}