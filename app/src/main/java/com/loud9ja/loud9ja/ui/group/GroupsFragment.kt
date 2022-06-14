package com.loud9ja.loud9ja.ui.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentGroupsBinding
import com.loud9ja.loud9ja.domain.TrendingGroups
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.UIstate
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [GroupsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class GroupsFragment : BindingFragment<FragmentGroupsBinding>() {
    private val viewModel: GroupViewModel by viewModels()
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
        viewModel.getGroup()
        observeGroups()
        binding.btnNewGroup.setOnClickListener {
            val fragment = NewGroupFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.commit()
        }


    }

    fun observeGroups() {
        viewModel.groupResponse.observe(viewLifecycleOwner) { result ->
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
                        adapter = trendingGroupsAdapter
                        trendingGroupsAdapter.addItems(result.data!!.data.data)

                    }

                    binding.popularRecyclerview.apply {
                        hasFixedSize()
                        layoutManager =
                            LinearLayoutManager(requireContext())
                        adapter = popularGroupsAdapter
                        isNestedScrollingEnabled = false
                        popularGroupsAdapter.addItems(result.data!!.data.data)

                    }

                }
                is UIstate.Loading -> {}
                is UIstate.Error -> {}
            }

        }
    }
}