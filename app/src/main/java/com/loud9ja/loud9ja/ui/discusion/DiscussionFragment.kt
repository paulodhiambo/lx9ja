package com.loud9ja.loud9ja.ui.discusion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentDiscussionBinding
import com.loud9ja.loud9ja.domain.Trending
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class DiscussionFragment : BindingFragment<FragmentDiscussionBinding>() {
    private val trendingAdapter by lazy {
        TrendingRecyclerViewAdapter()
    }
    private val recentAdapter by lazy {
        RecentRecyclerViewAdapter()
    }


    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentDiscussionBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.trendingRecyclerview.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingAdapter
            trendingAdapter.addItems(
                mutableListOf(
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", "")
                )
            )
            trendingAdapter.listener = { _, _, _ ->
                val fragment = DiscussionDetailFragment()
                val fragmentManager = fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                fragmentTransaction?.commit()
            }
        }
        binding.recentRecyclerview.apply {
            hasFixedSize()
            isNestedScrollingEnabled = false
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = recentAdapter
            recentAdapter.addItems(
                mutableListOf(
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", "")
                )
            )
            recentAdapter.listener = { _, _, _ ->
                val fragment = DiscussionDetailFragment()
                val fragmentManager = fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                fragmentTransaction?.commit()
            }
        }
    }
}