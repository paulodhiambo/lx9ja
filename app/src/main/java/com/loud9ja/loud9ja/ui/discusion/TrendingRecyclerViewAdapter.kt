package com.loud9ja.loud9ja.ui.discusion

import android.content.Intent
import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.TrendingItemBinding
import com.loud9ja.loud9ja.domain.network.api.trending.Data
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants.IMAGE_PATH


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
        Glide.with(holder.itemView).load("${IMAGE_PATH}${items[position].profilePicture}")
            .error(R.drawable.profile_image).into(holder.binding.profileImage)
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }

        holder.binding.shareButton.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey check out Loud9ja discussions at : https://play.google.com/store/apps/details?id=com.loud9ja.loud9ja"
            )
            sendIntent.type = "text/plain"
            holder.itemView.context.startActivity(sendIntent)
        }

        holder.binding.readButton.setOnClickListener {
            listener?.invoke(it, items[position], position)

        }
    }
}