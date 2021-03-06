package com.loud9ja.loud9ja.ui.polls

import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.EndingPollItemBinding
import com.loud9ja.loud9ja.domain.network.api.polls.PollResponseItem
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants

class EndingPollsRecyclerViewAdapter :
    BaseRecyclerViewAdapter<PollResponseItem, EndingPollItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.ending_poll_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<EndingPollItemBinding>,
        position: Int
    ) {
        holder.binding.polls = items[position]
        holder.binding.textView18.text = items[position].user!!.name
        holder.binding.textView35.text = items[position].question
        holder.binding.textView34.text = "Ends in ${items[position].endsIn} hours"
        Glide.with(holder.itemView)
            .load("${Constants.IMAGE_PATH}${items[position].user?.profilePicture}")
            .error(R.drawable.profile_image).into(holder.binding.profileImage)
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}