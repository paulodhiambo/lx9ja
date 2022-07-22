package com.loud9ja.loud9ja.ui.polls

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.PopularPollItemBinding
import com.loud9ja.loud9ja.domain.network.api.polls.PollResponseItem
import com.loud9ja.loud9ja.utils.BaseRecyclerViewAdapter
import com.loud9ja.loud9ja.utils.Constants.IMAGE_PATH
import com.loud9ja.loud9ja.utils.VoteListener
import kotlin.collections.set


class PopularPollsRecyclerViewAdapter :
    BaseRecyclerViewAdapter<PollResponseItem, PopularPollItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.popular_poll_item
    }

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<PopularPollItemBinding>,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val voteView = holder.binding.voteView
        val voteData = LinkedHashMap<String, Int>()
        items[position].options?.forEach {
            voteData[it?.option.toString()] = items[position].totalVotes!!
        }
        voteView.initVote(voteData);
        voteView.setAnimationRate(600);
        holder.binding.textView35.text = items[position].question
        holder.binding.textView18.text = items[position].user!!.name
        holder.binding.textView34.text = "End in ${items[position].endsIn} hours"
        Glide.with(holder.itemView).load("${IMAGE_PATH}${items[position].user!!.profilePicture}")
            .error(R.drawable.profile_image).into(holder.binding.profileImage)
        voteView.setVoteListener(object : VoteListener {
            override fun onItemClick(view: View?, index: Int, status: Boolean): Boolean {
                Log.d("Index==================>", "onItemClick: ${index}")
                if (!status) {
                    //
                } else {
                    voteView.notifyUpdateChildren(view, true)
                }
                votelistener?.invoke(
                    view!!,
                    items[position],
                    position,
                    items[position].id!!,
                    items[position].options!![index]!!.id!!
                )
                return true
            }
        })
        holder.binding.button4.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Voting...", Toast.LENGTH_LONG).show()
        }

        holder.binding.polls = items[position]
        //onclick event
        holder.binding.root.setOnClickListener {
            listener?.invoke(it, items[position], position)
        }
    }
}