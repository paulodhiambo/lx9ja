package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.network.api.registration.RegistrationResponse
import com.loud9ja.loud9ja.domain.network.api.request.LoginRequest
import com.loud9ja.loud9ja.domain.network.api.response.LoginResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: LoudAPI) :
    AuthRepository {
    override suspend fun registerUser(user: User): RegistrationResponse {
        return api.registerUser(user)
    }

    override suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        return api.loginUser(loginRequest)
    }

}