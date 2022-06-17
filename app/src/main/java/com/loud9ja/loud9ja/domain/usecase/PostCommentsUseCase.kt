package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.comments.PostCommentResponse
import com.loud9ja.loud9ja.domain.repository.PostsRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostCommentsUseCase @Inject constructor(private val repository: PostsRepository) {
    operator fun invoke(id: Int): Flow<NetworkState<PostCommentResponse>> = flow {
        try {
            emit(NetworkState.Loading())
            val result = repository.getPostComments(id)
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error(e.message.toString()))
        } catch (e: HttpException) {
            emit(NetworkState.Error(e.message()))
        }
    }
}