package com.loud9ja.loud9ja.ui.polls

import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.EndingPollItemBinding
import com.loud9ja.loud9ja.domain.network.api.polls.Poll
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants

class EndingPollsRecyclerViewAdapter : BaseRecyclerViewAdapter<Poll, EndingPollItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.ending_poll_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<EndingPollItemBinding>,
        position: Int
    ) {
        holder.binding.polls = items[position]
        holder.binding.textView18.text = items[position].createdBy
        holder.binding.textView35.text = items[position].question
        Glide.with(holder.itemView).load("${Constants.IMAGE_PATH}${items[position].profilePicture}")
            .error(R.drawable.profile_image).into(holder.binding.profileImage)
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}