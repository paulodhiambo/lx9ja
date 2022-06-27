package com.loud9ja.loud9ja.ui.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.data.DummyPostData
import com.loud9ja.loud9ja.databinding.FragmentGroupDetailBinding
import com.loud9ja.loud9ja.domain.network.api.groups.Group
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.Constants.IMAGE_PATH
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GroupDetailFragment : BindingFragment<FragmentGroupDetailBinding>() {
    val commentAdapter: GroupPostDetailRecyclerViewAdapter by lazy {
        GroupPostDetailRecyclerViewAdapter()
    }
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentGroupDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val group = args?.get("group") as Group
        Glide.with(requireContext()).load("${IMAGE_PATH}${group.media}").error(R.drawable.banner1)
            .into(binding.profileImage)
        binding.textViewPostAuthor.text = group.name
        binding.textViewPostContent.text = group.description
        val data = mutableListOf(
            DummyPostData("jn"),
            DummyPostData("jn"),
            DummyPostData("jn"),
            DummyPostData("jn"),
            DummyPostData("jn")
        )
        commentAdapter.items = data
        binding.postCommentRecycler.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = commentAdapter
        }
    }

}