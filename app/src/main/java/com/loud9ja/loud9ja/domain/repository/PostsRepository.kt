package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.api.posts.PostResponse
import com.loud9ja.loud9ja.domain.network.api.trending.TrendingPostResponse

interface PostsRepository {
    suspend fun getPosts(): PostResponse

    suspend fun getTrendingPosts():TrendingPostResponse
}