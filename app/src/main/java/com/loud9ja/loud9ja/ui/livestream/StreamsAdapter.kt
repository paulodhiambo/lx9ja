package com.loud9ja.loud9ja.ui.livestream

import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.StreamItemBinding
import com.loud9ja.loud9ja.domain.firebase.stream.LiveStream
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants

class StreamsAdapter :
    BaseRecyclerViewAdapter<LiveStream, StreamItemBinding>() {

    override fun getLayout(): Int {
        return R.layout.stream_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<StreamItemBinding>,
        position: Int
    ) {
        holder.binding.stream = items[position]
        holder.binding.textViewPostTitle.text = items[position].userName
        holder.binding.textViewPostContent.text = items[position].streamTitle
        Glide.with(holder.itemView).load("${Constants.IMAGE_PATH}${items[position].userImage}")
            .error(R.drawable.profile_image)
            .into(holder.binding.imageView5)
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}