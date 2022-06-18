package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.comments.AddCommentRequest
import com.loud9ja.loud9ja.domain.network.api.comments.AddCommentResponse
import com.loud9ja.loud9ja.domain.network.api.comments.PostCommentsResponse
import com.loud9ja.loud9ja.domain.repository.PostsRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddPostCommentUseCase @Inject constructor(private val repository: PostsRepository) {
    operator fun invoke(addCommentRequest: AddCommentRequest): Flow<NetworkState<AddCommentResponse>> = flow {
        try {
            emit(NetworkState.Loading())
            val result = repository.addPostComment(addCommentRequest)
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error(e.message.toString()))
        } catch (e: HttpException) {
            emit(NetworkState.Error(e.message()))
        }
    }
}