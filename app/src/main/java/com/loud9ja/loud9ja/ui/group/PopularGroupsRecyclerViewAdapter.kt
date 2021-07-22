package com.loud9ja.loud9ja.ui.group

import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.PopularGroupsItemBinding
import com.loud9ja.loud9ja.domain.TrendingGroups
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter

class PopularGroupsRecyclerViewAdapter :
    BaseRecyclerViewAdapter<TrendingGroups, PopularGroupsItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.popular_groups_item
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewAdapter.Companion.BaseViewHolder<PopularGroupsItemBinding>,
        position: Int
    ) {
        val images: ArrayList<Any> = ArrayList()
        images.add(R.drawable.one)
        images.add(R.drawable.three)
        images.add(R.drawable.two)

        holder.binding.stackProfiles.setImageLists(images)
        holder.binding.groups = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}