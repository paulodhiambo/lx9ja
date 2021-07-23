package com.loud9ja.loud9ja.ui.report

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentReportBinding
import com.loud9ja.loud9ja.domain.Report
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class ReportFragment : BindingFragment<FragmentReportBinding>() {
    private val reportAdapter by lazy {
        ReportRecyclerViewAdapter()
    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentReportBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reportsRecyclerview.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reportAdapter
            reportAdapter.addItems(
                mutableListOf(
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, false),
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, false),
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, true)


                )
            )
            reportAdapter.listener = { _, item, _ ->
                val fragment = ReportDetailFragment()
                val fragmentManager = fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                fragmentTransaction?.commit()
            }
        }

        binding.btnNewReport.setOnClickListener {
            val fragment = NewReportFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.commit()
        }

    }
}