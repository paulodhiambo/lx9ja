package com.loud9ja.loud9ja.ui.discusion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentPostBinding
import com.loud9ja.loud9ja.utils.BindingFragment

class PostFragment : BindingFragment<FragmentPostBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPostBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}