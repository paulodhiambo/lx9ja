package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.profile.UserProfileResponse
import com.loud9ja.loud9ja.domain.repository.ProfileRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProfileUserCase @Inject constructor(private val profileRepository: ProfileRepository) {
    operator fun invoke(): Flow<NetworkState<UserProfileResponse>> = flow {
        try {
            emit(NetworkState.Loading())
            val result = profileRepository.getUserProfile()
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error(e.message ?: "No internet connection"))
        } catch (e: HttpException) {
            emit(NetworkState.Error(e.message()))
        }
    }
}