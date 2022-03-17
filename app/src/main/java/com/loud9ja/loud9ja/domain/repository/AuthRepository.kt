package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.domain.network.api.request.LoginRequest
import com.loud9ja.loud9ja.domain.network.api.response.LoginResponse
import com.loud9ja.loud9ja.domain.network.api.response.RegistrationResponse
import io.reactivex.Observable

interface AuthRepository {
    suspend fun registerUser(user: User): RegistrationResponse
    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse
}