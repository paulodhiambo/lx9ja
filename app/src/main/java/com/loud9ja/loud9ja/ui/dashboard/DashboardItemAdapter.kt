package com.loud9ja.loud9ja.ui.dashboard

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.DashboardItemsBinding
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter

class DashboardItemAdapter :
    BaseRecyclerViewAdapter<DashboardItems, DashboardItemsBinding>() {

    override fun getLayout(): Int {
        return R.layout.dashboard_items
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<DashboardItemsBinding>,
        position: Int
    ) {
        holder.binding.items = items[position]
        holder.itemView.setOnClickListener {
            listener?.invoke(holder.itemView, items[position], position)
        }
    }
}