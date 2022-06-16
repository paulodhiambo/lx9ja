package com.loud9ja.loud9ja.ui.discusion

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.TrendingItemBinding
import com.loud9ja.loud9ja.domain.network.api.trending.Data
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter

class TrendingRecyclerViewAdapter : BaseRecyclerViewAdapter<Data, TrendingItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.trending_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<TrendingItemBinding>,
        position: Int
    ) {
        holder.binding.post = items[position]
        holder.binding.textView24.text = items[position].comments.toString()
        holder.binding.textView23.text = items[position].totalDislikes.toString()
        holder.binding.textView22.text = items[position].totalLikes.toString()
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}