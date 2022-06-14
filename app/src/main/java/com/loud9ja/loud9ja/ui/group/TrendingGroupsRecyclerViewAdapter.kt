package com.loud9ja.loud9ja.ui.group

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.TrendingGroupItemBinding
import com.loud9ja.loud9ja.domain.TrendingGroups
import com.loud9ja.loud9ja.domain.network.api.groups.Group
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter

class TrendingGroupsRecyclerViewAdapter :
    BaseRecyclerViewAdapter<Group, TrendingGroupItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.trending_group_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<TrendingGroupItemBinding>,
        position: Int
    ) {
        val images: ArrayList<Any> = ArrayList()
        images.add(R.drawable.one)
        images.add(R.drawable.three)
        images.add(R.drawable.two)

        holder.binding.stackProfiles.setImageLists(images)
        holder.binding.trendinggroups = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}