package com.loud9ja.loud9ja.ui.polls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentCreatePollBinding
import com.loud9ja.loud9ja.utils.BindingFragment

class CreatePollFragment : BindingFragment<FragmentCreatePollBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentCreatePollBinding::inflate
}