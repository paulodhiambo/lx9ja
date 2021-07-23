package com.loud9ja.loud9ja.ui.polls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentPollsBinding
import com.loud9ja.loud9ja.domain.Trending
import com.loud9ja.loud9ja.ui.discusion.DiscussionDetailFragment
import com.loud9ja.loud9ja.utils.BindingFragment


class PollsFragment : BindingFragment<FragmentPollsBinding>() {

    private val popularPollsAdapter by lazy {
        PopularPollsRecyclerViewAdapter()
    }
    private val endingPollsAdapter by lazy {
        EndingPollsRecyclerViewAdapter()
    }
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPollsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.trendingRecyclerview.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = popularPollsAdapter
            popularPollsAdapter.addItems(
                mutableListOf(
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", "")
                )
            )
        }

        binding.recentRecyclerview.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = endingPollsAdapter
            endingPollsAdapter.addItems(
                mutableListOf(
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", "")
                )
            )
        }
    }
}