package com.loud9ja.loud9ja.ui.group

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.data.DummyPostData
import com.loud9ja.loud9ja.databinding.GroupPostItemBinding
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter

class GroupPostDetailRecyclerViewAdapter :
    BaseRecyclerViewAdapter<DummyPostData, GroupPostItemBinding>() {
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
    }
}