package com.loud9ja.loud9ja.ui.discusion

import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ListItemCommentBinding
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants.IMAGE_PATH

class CommentRecyclerViewAdapter :
    BaseRecyclerViewAdapter<com.loud9ja.loud9ja.domain.network.api.comments.Comment, ListItemCommentBinding>() {
    override fun getLayout(): Int {
        return R.layout.list_item_comment
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ListItemCommentBinding>,
        position: Int
    ) {
        holder.binding.comment = items[position]
        Glide.with(holder.itemView).load("${IMAGE_PATH}${items[position].profilePicture}")
            .error(R.drawable.profile_image)
            .into(holder.binding.profileImag)
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}