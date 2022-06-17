package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.network.api.comments.PostCommentResponse
import com.loud9ja.loud9ja.domain.network.api.posts.PostResponse
import com.loud9ja.loud9ja.domain.network.api.trending.TrendingPostResponse
import javax.inject.Inject

class PostsRepository @Inject constructor(private val api: LoudAPI) {
    suspend fun getPosts(): PostResponse = api.getPosts()
    suspend fun getTrendingPosts():TrendingPostResponse = api.getTrendingPosts()
    suspend fun getPostComments(id: Int): PostCommentResponse = api.getPostComments(id)
}