package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.api.posts.PostResponse

interface PostsRepository {
    suspend fun getPosts(): PostResponse
}