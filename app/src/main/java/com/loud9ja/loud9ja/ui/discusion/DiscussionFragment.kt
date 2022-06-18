package com.loud9ja.loud9ja.ui.discusion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentDiscussionBinding
import com.loud9ja.loud9ja.domain.network.api.trending.Data
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class DiscussionFragment : BindingFragment<FragmentDiscussionBinding>() {
    private val viewModel: DiscussionViewModel by viewModels()
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
        viewModel.getTrendingPosts()
        observeTrending(binding)

        binding.btnNewPost.setOnClickListener {
            val fragment = PostFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.commit()
        }
    }

    private fun observeTrending(binding: FragmentDiscussionBinding) {
        viewModel.postsResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataState.Success -> {
                    trendingAdapter.items = result.data.data as MutableList<Data>
                    binding.trendingRecyclerview.apply {
                        hasFixedSize()
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(), LinearLayoutManager.HORIZONTAL, false
                            )
                        adapter = trendingAdapter
                        trendingAdapter.listener = { _, item, _ ->
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

                    binding.recentRecyclerview.apply {
                        hasFixedSize()
                        isNestedScrollingEnabled = false
                        layoutManager =
                            LinearLayoutManager(requireContext())
                        adapter = recentAdapter
                        recentAdapter.addItems(result.data.data)
                        recentAdapter.listener = { _, item, _ ->
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