package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.groups.GroupResponse
import com.loud9ja.loud9ja.domain.repository.GroupRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetgroupUseCase @Inject constructor(private val repository: GroupRepository) {
    operator fun invoke(): Flow<NetworkState<GroupResponse>> = flow {
        try {
            emit(NetworkState.Loading())
            val result = repository.getGroups()
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error("No internet"))

        } catch (e: HttpException) {
            emit(NetworkState.Error("An error occurred"))

        }
    }
}