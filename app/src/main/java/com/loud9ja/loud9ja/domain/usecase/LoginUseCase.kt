package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.request.LoginRequest
import com.loud9ja.loud9ja.domain.network.api.response.LoginResponse
import com.loud9ja.loud9ja.domain.repository.AuthRepository
import com.loud9ja.loud9ja.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(loginRequest: LoginRequest): Flow<DataState<LoginResponse>> = flow {
        try {
            val result = repository.loginUser(loginRequest)
            emit(DataState.Success(result))
        } catch (e: HttpException) {
            emit(DataState.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(DataState.Error(e.localizedMessage ?: "No internet connection"))
        }
    }
}