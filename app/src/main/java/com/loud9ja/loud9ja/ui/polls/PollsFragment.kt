package com.loud9ja.loud9ja.ui.polls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentPollsBinding
import com.loud9ja.loud9ja.domain.Trending
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.UIstate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PollsFragment : BindingFragment<FragmentPollsBinding>() {
    private val viewModel: PollsViewModel by viewModels()

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
        viewModel.getPolls()
        observePollResponse()
    }

    private fun observePollResponse() {
        viewModel.pollResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is UIstate.Success -> {
                    binding.trendingRecyclerview.apply {
                        hasFixedSize()
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                        adapter = popularPollsAdapter
                        popularPollsAdapter.addItems(result.data!!.data)
                    }

                    binding.recentRecyclerview.apply {
                        hasFixedSize()
                        layoutManager =
                            LinearLayoutManager(requireContext())
                        adapter = endingPollsAdapter
                        endingPollsAdapter.addItems(result.data!!.data)
                    }
                }
                is UIstate.Loading -> {}
                is UIstate.Error -> {}
            }

        }
    }
}