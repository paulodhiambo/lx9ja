package com.loud9ja.loud9ja.ui.report

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ReportItemBinding
import com.loud9ja.loud9ja.domain.Report
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter

class ReportRecyclerViewAdapter : BaseRecyclerViewAdapter<Report, ReportItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.report_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ReportItemBinding>,
        position: Int
    ) {
        holder.binding.report = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}