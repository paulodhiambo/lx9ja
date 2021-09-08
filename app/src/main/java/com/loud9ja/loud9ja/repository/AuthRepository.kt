package com.loud9ja.loud9ja.repository

import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.network.api.request.LoginRequest
import com.loud9ja.loud9ja.network.api.response.LoginResponse
import com.loud9ja.loud9ja.network.api.response.RegistrationResponse
import io.reactivex.Observable

interface AuthRepository {
    fun registerUser(user: User): Observable<RegistrationResponse>
    fun loginUser(loginRequest: LoginRequest): Observable<LoginResponse>
}