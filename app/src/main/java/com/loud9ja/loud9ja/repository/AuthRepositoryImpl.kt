package com.loud9ja.loud9ja.repository

import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.network.LoudAPI
import com.loud9ja.loud9ja.network.api.request.LoginRequest
import com.loud9ja.loud9ja.network.api.response.LoginResponse
import com.loud9ja.loud9ja.network.api.response.RegistrationResponse
import io.reactivex.Observable

class AuthRepositoryImpl constructor(private val api: LoudAPI) :
    AuthRepository {
    override fun registerUser(user: User): Observable<RegistrationResponse> {
        return api.registerUser(user)
    }

    override fun loginUser(loginRequest: LoginRequest): Observable<LoginResponse> {
        return api.loginUser(loginRequest)
    }

}