package com.loud9ja.loud9ja.ui.polls

import android.view.View
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.PopularPollItemBinding
import com.loud9ja.loud9ja.domain.Trending
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.VoteListener
import java.util.*


class PopularPollsRecyclerViewAdapter :
    BaseRecyclerViewAdapter<Trending, PopularPollItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.popular_poll_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<PopularPollItemBinding>,
        position: Int
    ) {
        val voteView = holder.binding.voteView
        val voteData = LinkedHashMap<String, Int>()
        voteData["Less than 1 cup"] = 0
        voteData["1-4 cups of coffee"] = 75
        voteData["5 cups of coffee or more"] = 25
        voteView.initVote(voteData);
        voteView.setAnimationRate(600);

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