package com.loud9ja.loud9ja.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentReportBinding
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class ReportFragment : BindingFragment<FragmentReportBinding>() {
    private val viewModel: ReportViewModel by viewModels()
    private val reportAdapter by lazy {
        ReportRecyclerViewAdapter()
    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentReportBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getReports()
        observe()

        binding.btnNewReport.setOnClickListener {
            val fragment = NewReportFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.commit()
        }

    }

    fun observe() {
        viewModel.getReportState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataState.Success -> {
                    binding.reportsRecyclerview.apply {
                        hasFixedSize()
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = reportAdapter
                        reportAdapter.addItems(result.data.data)
                        reportAdapter.listener = { _, item, _ ->
                            val bundle = Bundle()
                            bundle.putSerializable("report", item)
                            val fragment = ReportDetailFragment()
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
                is DataState.Error -> {}
                is DataState.Loading -> {}
            }
        }
    }
}