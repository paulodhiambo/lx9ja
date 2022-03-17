package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.domain.network.api.response.RegistrationResponse
import com.loud9ja.loud9ja.domain.repository.AuthRepository
import com.loud9ja.loud9ja.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(user: User): Flow<DataState<RegistrationResponse>> = flow {
        try {
            val result = repository.registerUser(user)
            emit(DataState.Success(data = result))
        } catch (e: HttpException) {
            emit(DataState.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(
                DataState.Error(
                    e.localizedMessage ?: "No internet connection"
                )
            )
        }
    }
}