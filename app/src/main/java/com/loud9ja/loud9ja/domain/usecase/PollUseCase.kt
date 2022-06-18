package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.polls.PollResponse
import com.loud9ja.loud9ja.domain.repository.PollRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PollUseCase @Inject constructor(private val pollRepository: PollRepository) {
    operator fun invoke(): Flow<NetworkState<PollResponse>> = flow {
        try {
            emit(NetworkState.Loading())
            val result = pollRepository.getPolls()
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error(e.message!!))
        } catch (e: HttpException) {
            emit(NetworkState.Error(e.message()))
        }
    }
}