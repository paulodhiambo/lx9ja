package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.network.api.posts.PostResponse
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(private val api: LoudAPI) : PostsRepository {
    override suspend fun getPosts(): PostResponse = api.getPosts()
}