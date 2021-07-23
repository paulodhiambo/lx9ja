package com.loud9ja.loud9ja.ui.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentGroupsBinding
import com.loud9ja.loud9ja.domain.TrendingGroups
import com.loud9ja.loud9ja.utils.BindingFragment


/**
 * A simple [Fragment] subclass.
 * Use the [GroupsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GroupsFragment : BindingFragment<FragmentGroupsBinding>() {
    private val trendingGroupsAdapter by lazy {
        TrendingGroupsRecyclerViewAdapter()
    }

    private val popularGroupsAdapter by lazy {
        PopularGroupsRecyclerViewAdapter()
    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentGroupsBinding::inflate

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
}