package com.loud9ja.loud9ja.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentReportDetailBinding
import com.loud9ja.loud9ja.domain.network.api.reports.Data
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportDetailFragment : BindingFragment<FragmentReportDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentReportDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val report = args?.get("report") as Data
        binding.tvReportTitle.text = report.title
        binding.textViewReportContent.text = report.message
        binding.textViewReportCreatedAt.text = report.createdAt
    }

}