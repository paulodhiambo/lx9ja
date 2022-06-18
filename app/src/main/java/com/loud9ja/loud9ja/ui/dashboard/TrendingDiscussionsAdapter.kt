package com.loud9ja.loud9ja.ui.dashboard

import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.TrendingDashboardDiscussionsBinding
import com.loud9ja.loud9ja.domain.network.api.trending.Data
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants.IMAGE_PATH

class TrendingDiscussionsAdapter :
    BaseRecyclerViewAdapter<Data, TrendingDashboardDiscussionsBinding>() {

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
        Glide.with(holder.itemView).load("${IMAGE_PATH}${items[position].profilePicture}")
            .error(R.drawable.profile_image)
            .into(holder.binding.imageView5)
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}