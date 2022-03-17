package com.loud9ja.loud9ja.domain.network

import com.google.firebase.auth.FirebaseAuth
import com.loud9ja.loud9ja.utils.AuthPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authPreference: AuthPreference,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val requestBuilder = chain.request().newBuilder()
        authPreference.getAuthDetails()?.let {
            requestBuilder.addHeader("Authorization", "Bearer ${it.accessToken}")
        }
        val response = chain.proceed(requestBuilder.build())
        if (response.code == 405 || response.code == 401 || response.code == 403) {
            authPreference.removeData()
            mAuth.signOut()
        }
        return response
    }
}