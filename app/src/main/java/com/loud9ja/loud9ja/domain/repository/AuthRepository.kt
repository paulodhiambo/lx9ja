package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.domain.network.api.auth.LoginResponse
import com.loud9ja.loud9ja.domain.network.api.registration.RegistrationResponse
import com.loud9ja.loud9ja.domain.network.api.request.LoginRequest

interface AuthRepository {
    suspend fun registerUser(user: User): RegistrationResponse
    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse
}