package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.firebase.poll.CreatePollRequest
import com.loud9ja.loud9ja.domain.repository.PollRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreatePollUseCase @Inject constructor(private val repository: PollRepository) {
    operator fun invoke(pollRequest: CreatePollRequest): Flow<NetworkState<ResponseBody>> = flow {
        try {
            emit(NetworkState.Loading())
            val result = repository.createPoll(pollRequest)
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error(e.message!!))
        } catch (e: HttpException) {
            emit(NetworkState.Error(e.message()))
        }
    }
}