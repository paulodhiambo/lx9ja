package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.posts.LikePostRequest
import com.loud9ja.loud9ja.domain.network.api.posts.LikePostResponse
import com.loud9ja.loud9ja.domain.repository.PostsRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LikePostUseCase @Inject constructor(private val repository: PostsRepository) {
    operator fun invoke(likePostRequest: LikePostRequest): Flow<NetworkState<LikePostResponse>> = flow {
        try {
            emit(NetworkState.Loading())
            val result = repository.likePost(likePostRequest)
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error(e.message.toString()))
        } catch (e: HttpException) {
            emit(NetworkState.Error(e.message()))
        }
    }
}