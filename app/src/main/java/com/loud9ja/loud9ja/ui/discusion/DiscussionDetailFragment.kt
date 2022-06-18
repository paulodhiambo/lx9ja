package com.loud9ja.loud9ja.ui.discusion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentDiscussionDetailBinding
import com.loud9ja.loud9ja.domain.network.api.comments.AddCommentRequest
import com.loud9ja.loud9ja.domain.network.api.comments.Comment
import com.loud9ja.loud9ja.domain.network.api.trending.Data
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscussionDetailFragment : BindingFragment<FragmentDiscussionDetailBinding>() {
    private val discussionViewModel: DiscussionViewModel by viewModels()
    private val commentsAdapter: CommentRecyclerViewAdapter by lazy {
        CommentRecyclerViewAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val post = args?.get("post") as Data
        discussionViewModel.getPostComments(post.id)
        observerGetPostComments(binding)
        binding.tvPostTitle.text = post.title
        binding.tvPostContent.text = post.description
        binding.textViewCommentCount.text = post.comments.toString()
        binding.tvPotsCreatedBy.text = post.createdBy.plus(" | ").plus(post.createdAt)
        binding.textViewLikeCount.text = post.likes.toString()
        binding.textViewDislikeCount.text = post.totalDislikes.toString()
        binding.btnSendComment.setOnClickListener {
            val comment = binding.textInputComment.text.toString().trim()
            val addCommentRequest = AddCommentRequest(comment, post.id)
            discussionViewModel.addPostComment(addCommentRequest)
            observerAddComment(binding)
            discussionViewModel.getPostComments(post.id)
            observerGetPostComments(binding)
        }
    }

    private fun observerAddComment(binding: FragmentDiscussionDetailBinding) {
        discussionViewModel.addCommentsResponse.observe(viewLifecycleOwner){result->
            when (result) {
                is DataState.Success -> {
                    Toast.makeText(requireContext(), "Comment added successfully", Toast.LENGTH_LONG).show()
                }
                is DataState.Loading -> {}
                is DataState.Error -> {}
            }
        }
    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentDiscussionDetailBinding::inflate

    private fun observerGetPostComments(binding: FragmentDiscussionDetailBinding) {
        discussionViewModel.postCommentsResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataState.Success -> {
                    val comments = result.data.data.data
                    commentsAdapter.clearItems()
                    commentsAdapter.addItems(comments)
                    commentsAdapter.items = comments as MutableList<Comment>
                    binding.rvPostComments.apply {
                        hasFixedSize()
                        layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        adapter = commentsAdapter
                    }
                }
                is DataState.Loading -> {}
                is DataState.Error -> {}
            }
        }
    }
}
