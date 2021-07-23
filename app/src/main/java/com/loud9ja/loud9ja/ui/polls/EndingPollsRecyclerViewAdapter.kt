package com.loud9ja.loud9ja.ui.polls

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.EndingPollItemBinding
import com.loud9ja.loud9ja.domain.Trending
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter

class EndingPollsRecyclerViewAdapter : BaseRecyclerViewAdapter<Trending, EndingPollItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.ending_poll_item
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewAdapter.Companion.BaseViewHolder<EndingPollItemBinding>,
        position: Int
    ) {
        holder.binding.polls = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}