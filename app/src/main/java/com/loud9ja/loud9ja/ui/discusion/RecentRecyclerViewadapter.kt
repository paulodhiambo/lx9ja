package com.loud9ja.loud9ja.ui.discusion

import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.RecentItemBinding
import com.loud9ja.loud9ja.domain.network.api.trending.Data
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants


class RecentRecyclerViewAdapter : BaseRecyclerViewAdapter<Data, RecentItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.recent_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<RecentItemBinding>,
        position: Int
    ) {
        holder.binding.recent = items[position]
        holder.binding.textView18.text = items[position].user.name
        holder.binding.textView20.text = items[position].title
        holder.binding.textView21.text = items[position].description
        holder.binding.textView24.text = items[position].comments.toString()
        holder.binding.textView22.text = items[position].likes.toString()
        holder.binding.textView23.text = items[position].totalDislikes.toString()
        Glide.with(holder.itemView).load("${Constants.IMAGE_PATH}${items[position].profilePicture}")
            .error(R.drawable.profile_image).into(holder.binding.profileImage)
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}