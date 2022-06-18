package com.loud9ja.loud9ja.ui.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentGroupDetailBinding
import com.loud9ja.loud9ja.databinding.FragmentGroupsBinding
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GroupDetailFragment : BindingFragment<FragmentGroupDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentGroupsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root
    }

}