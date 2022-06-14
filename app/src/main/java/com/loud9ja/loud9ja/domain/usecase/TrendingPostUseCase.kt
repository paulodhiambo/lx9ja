package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.trending.TrendingPostResponse
import com.loud9ja.loud9ja.domain.repository.PostsRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TrendingPostUseCase @Inject constructor(private val postsRepository: PostsRepository) {
    operator fun invoke(): Flow<NetworkState<TrendingPostResponse>> = flow {
        try {
            emit(NetworkState.Loading())
            val result = postsRepository.getTrendingPosts()
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error(e.message.toString()))
        } catch (e: HttpException) {
            emit(NetworkState.Error(e.message()))
        }
    }
}