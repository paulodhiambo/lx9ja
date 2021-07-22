package com.loud9ja.loud9ja.ui.discusion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentDiscussionDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DiscussionDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscussionDetailFragment : Fragment() {
    private var _binding: FragmentDiscussionDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDiscussionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}