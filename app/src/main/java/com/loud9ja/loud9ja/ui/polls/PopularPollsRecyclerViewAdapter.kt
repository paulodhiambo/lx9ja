package com.loud9ja.loud9ja.ui.polls

import android.view.View
import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.PopularPollItemBinding
import com.loud9ja.loud9ja.domain.network.api.polls.Poll
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants.IMAGE_PATH
import com.loud9ja.loud9ja.utils.VoteListener


class PopularPollsRecyclerViewAdapter :
    BaseRecyclerViewAdapter<Poll, PopularPollItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.popular_poll_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<PopularPollItemBinding>,
        position: Int
    ) {
        val voteView = holder.binding.voteView
        val voteData = LinkedHashMap<String, Int>()
        items[position].options.forEach {
            voteData[it.option] = 20 * position
        }
        voteView.initVote(voteData);
        voteView.setAnimationRate(600);
        holder.binding.textView35.text = items[position].question
        holder.binding.textView18.text = items[position].createdBy
        Glide.with(holder.itemView).load("${IMAGE_PATH}${items[position].profilePicture}")
            .error(R.drawable.profile_image).into(holder.binding.profileImage)
        voteView.setVoteListener(object : VoteListener {
            override fun onItemClick(view: View?, index: Int, status: Boolean): Boolean {
                if (!status) {
                    // showDialog(voteView, view) //投票取消 dialog 处理详见 Sample MainActivity
                } else {
                    voteView.notifyUpdateChildren(view, true)
                }
                return true
            }
        })
        holder.binding.polls = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}