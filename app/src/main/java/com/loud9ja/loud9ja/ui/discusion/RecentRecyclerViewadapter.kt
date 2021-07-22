package com.loud9ja.loud9ja.ui.home

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.RecentItemBinding
import com.loud9ja.loud9ja.domain.Trending
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter


class RecentRecyclerViewAdapter : BaseRecyclerViewAdapter<Trending, RecentItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.recent_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<RecentItemBinding>,
        position: Int
    ) {
        holder.binding.recent = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}