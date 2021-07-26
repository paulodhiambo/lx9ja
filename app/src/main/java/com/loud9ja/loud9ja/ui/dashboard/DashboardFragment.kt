package com.loud9ja.loud9ja.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentDashboardBinding
import com.loud9ja.loud9ja.utils.BindingFragment


class DashboardFragment : BindingFragment<FragmentDashboardBinding>() {
    private val itemsAdapter by lazy {
        DashboardItemAdapter()
    }

    private val trendingItemsAdapter by lazy {
        TrendingDiscussionsAdapter()
    }
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentDashboardBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = mutableListOf(
            DashboardItems(R.drawable.ic_live_icon, "Go Live", "Start streaming"),
            DashboardItems(R.drawable.discussions, "Info", "Latest discussion"),
            DashboardItems(R.drawable.megaphone, "Report", "Recent happenings")
        )
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = itemsAdapter
            itemsAdapter.addItems(items)
        }

        binding.trendingRecycler.apply {
            hasFixedSize()
            isNestedScrollingEnabled = false
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = trendingItemsAdapter
            trendingItemsAdapter.addItems(items)
        }

    }
}