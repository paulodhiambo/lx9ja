package com.loud9ja.loud9ja.ui.discusion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentDiscussionDetailBinding
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [DiscussionDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DiscussionDetailFragment : BindingFragment<FragmentDiscussionDetailBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentDiscussionDetailBinding::inflate
}