package com.loud9ja.loud9ja.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentGroupsBinding
import com.loud9ja.loud9ja.domain.TrendingGroups


/**
 * A simple [Fragment] subclass.
 * Use the [GroupsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GroupsFragment : Fragment() {
    private var _binding: FragmentGroupsBinding? = null
    private val binding get() = _binding!!
    private val trendingGroupsAdapter by lazy {
        TrendingGroupsRecyclerViewAdapter()
    }

    private val popularGroupsAdapter by lazy {
        PopularGroupsRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGroupsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.trendingRecyclerview.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingGroupsAdapter
            trendingGroupsAdapter.addItems(
                mutableListOf(
                    TrendingGroups(""),
                    TrendingGroups(""),
                    TrendingGroups(""),
                    TrendingGroups("")
                )
            )

        }
        binding.popularRecyclerview.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = popularGroupsAdapter
            isNestedScrollingEnabled = false
            popularGroupsAdapter.addItems(
                mutableListOf(
                    TrendingGroups(""),
                    TrendingGroups(""),
                    TrendingGroups(""),
                    TrendingGroups("")
                )
            )

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}