package com.loud9ja.loud9ja.ui.dashboard

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.TrendingDashboardDiscussionsBinding
import com.loud9ja.loud9ja.domain.network.api.trending.Data
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter

class TrendingDiscussionsAdapter : BaseRecyclerViewAdapter<Data, TrendingDashboardDiscussionsBinding>() {

    override fun getLayout(): Int {
        return R.layout.trending_dashboard_discussions
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<TrendingDashboardDiscussionsBinding>,
        position: Int
    ) {
        holder.binding.items = items[position]
        holder.binding.textViewPostTitle.text = items[position].title
        holder.binding.textViewPostContent.text = items[position].description
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}