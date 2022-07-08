package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.polls.VoteRequest
import com.loud9ja.loud9ja.domain.repository.PollRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class VoteUseCase @Inject constructor(private val pollsRepository: PollRepository) {
    operator fun invoke(voteRequest: VoteRequest): Flow<NetworkState<String>> = flow {
        try {
            emit(NetworkState.Loading())
            pollsRepository.vote(voteRequest)
            emit(NetworkState.Success("success"))
        } catch (e: IOException) {
            emit(NetworkState.Error(e.message!!))
        } catch (e: HttpException) {
            emit(NetworkState.Error(e.message()))
        }
    }
}