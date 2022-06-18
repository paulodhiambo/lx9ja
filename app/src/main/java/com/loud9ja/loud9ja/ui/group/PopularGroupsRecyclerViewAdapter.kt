package com.loud9ja.loud9ja.ui.group

import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.PopularGroupsItemBinding
import com.loud9ja.loud9ja.domain.network.api.groups.Group
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants

class PopularGroupsRecyclerViewAdapter :
    BaseRecyclerViewAdapter<Group, PopularGroupsItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.popular_groups_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<PopularGroupsItemBinding>,
        position: Int
    ) {
        val images: ArrayList<Any> = ArrayList()
        images.add(R.drawable.one)
        images.add(R.drawable.three)
        images.add(R.drawable.two)

        holder.binding.stackProfiles.setImageLists(images)
        holder.binding.groups = items[position]
        Glide.with(holder.itemView).load("${Constants.IMAGE_PATH}${items[position].media}")
            .error(R.drawable.banner1).into(holder.binding.profileImage)
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}