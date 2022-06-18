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
        holder.binding.textViewCommentCount.text = items[position].comments.toString()
        holder.binding.textViewDislikeCount.text = items[position].totalDislikes.toString()
        holder.binding.textViewLikeCount.text = items[position].totalLikes.toString()
        holder.binding.textViewPostAuthor.text = items[position].createdBy
        holder.binding.textViewCreateAt.text = items[position].createdAt
        holder.binding.textViewPostContent.text = items[position].description
        holder.binding.textViewPostTitle.text = items[position].title
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}