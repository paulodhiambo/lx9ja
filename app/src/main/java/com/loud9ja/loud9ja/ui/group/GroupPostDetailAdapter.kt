package com.loud9ja.loud9ja.ui.group

import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.GroupPostItemBinding
import com.loud9ja.loud9ja.domain.firebase.GroupPost
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants

class GroupPostDetailRecyclerViewAdapter :
    BaseRecyclerViewAdapter<GroupPost, GroupPostItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.group_post_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<GroupPostItemBinding>,
        position: Int
    ) {
        holder.binding.data = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
        Glide.with(holder.itemView.context)
            .load("${Constants.IMAGE_PATH}${items[position].userImage}")
            .error(R.drawable.profile_image)
            .into(holder.binding.profileImage)
    }
}