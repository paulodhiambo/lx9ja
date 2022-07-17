package com.loud9ja.loud9ja.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentDashboardBinding
import com.loud9ja.loud9ja.domain.network.api.trending.Data
import com.loud9ja.loud9ja.ui.discusion.DiscussionDetailFragment
import com.loud9ja.loud9ja.ui.discusion.DiscussionViewModel
import com.loud9ja.loud9ja.ui.livestream.StreamsFragment
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BindingFragment<FragmentDashboardBinding>() {
    private val itemsAdapter by lazy {
        DashboardItemAdapter()
    }

    private val discussionViewModel: DiscussionViewModel by viewModels()
    private val trendingItemsAdapter by lazy {
        TrendingDiscussionsAdapter()
    }
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentDashboardBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = mutableListOf(
            DashboardItems(R.drawable.ic_live_icon, "Go Live", "Start streaming"),
            DashboardItems(R.drawable.icon_info, "Info", "Latest discussion"),
            DashboardItems(R.drawable.megaphone, "Report", "Recent happenings")
        )
        binding.recyclerView.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = itemsAdapter
            itemsAdapter.addItems(items)
        }

        itemsAdapter.listener = { _, _, p ->
            if (p == 0) {
                val fragment = StreamsFragment()
                val fragmentManager = fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                fragmentTransaction?.commit()
            }
        }
        binding.recentPostShimmerLayout.startShimmer()
        discussionViewModel.getTrendingPosts()
        observerGetTrendingPosts(binding)

    }

    private fun observerGetTrendingPosts(binding: FragmentDashboardBinding) {
        discussionViewModel.postsResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataState.Success -> {
                    binding.recentPostShimmerLayout.stopShimmer()
                    binding.recentPostShimmerLayout.visibility = View.GONE
                    binding.trendingRecycler.visibility = View.VISIBLE
                    trendingItemsAdapter.items = result.data.data as MutableList<Data>
                    binding.trendingRecycler.apply {
                        hasFixedSize()
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(), LinearLayoutManager.VERTICAL, false
                            )
                        adapter = trendingItemsAdapter
                        trendingItemsAdapter.listener = { _, item, _ ->
                            val bundle = Bundle()
                            bundle.putSerializable("post", item)
                            val fragment = DiscussionDetailFragment()
                            fragment.arguments = bundle
                            val fragmentManager = fragmentManager
                            val fragmentTransaction = fragmentManager?.beginTransaction()
                            fragmentTransaction?.replace(
                                R.id.nav_host_fragment_content_main,
                                fragment
                            )
                            fragmentTransaction?.commit()
                        }
                    }
                }
                is DataState.Loading -> {}
                is DataState.Error -> {}
            }

        }
    }
}