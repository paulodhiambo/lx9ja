package com.loud9ja.loud9ja.ui.discusion

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.TrendingItemBinding
import com.loud9ja.loud9ja.domain.Trending
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter

class TrendingRecyclerViewAdapter : BaseRecyclerViewAdapter<Trending, TrendingItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.trending_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<TrendingItemBinding>,
        position: Int
    ) {
        holder.binding.trending = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}