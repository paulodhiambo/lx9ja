package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.network.api.comments.AddCommentRequest
import com.loud9ja.loud9ja.domain.network.api.comments.AddCommentResponse
import com.loud9ja.loud9ja.domain.network.api.comments.PostCommentsResponse
import com.loud9ja.loud9ja.domain.network.api.posts.LikePostRequest
import com.loud9ja.loud9ja.domain.network.api.posts.LikePostResponse
import com.loud9ja.loud9ja.domain.network.api.posts.PostResponse
import com.loud9ja.loud9ja.domain.network.api.trending.TrendingPostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Part
import javax.inject.Inject

class PostsRepository @Inject constructor(private val api: LoudAPI) {
    suspend fun getPosts(): PostResponse = api.getPosts()
    suspend fun getTrendingPosts(): TrendingPostResponse = api.getTrendingPosts()
    suspend fun getPostComments(id: Int): PostCommentsResponse = api.getPostComments(id)
    suspend fun addPostComment(addCommentRequest: AddCommentRequest): AddCommentResponse =
        api.addPostComments(addCommentRequest)
    suspend fun likePost(likePostRequest: LikePostRequest): LikePostResponse = api.likePost(likePostRequest)

    suspend fun createPost(
        title: RequestBody,
        description: RequestBody,
        media: MultipartBody.Part?
    ): ResponseBody = api.createPost(title, description, media)
}