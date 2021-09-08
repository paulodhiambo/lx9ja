package com.loud9ja.loud9ja.network

import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.network.api.request.LoginRequest
import com.loud9ja.loud9ja.network.api.response.LoginResponse
import com.loud9ja.loud9ja.network.api.response.ProfileResponse
import com.loud9ja.loud9ja.network.api.response.RegistrationResponse
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface LoudAPI {
    companion object {
        const val REGISTER_API = "auth/register"
        const val LOGIN_API = "auth"
        const val SEND_RESET_PASSWORD_OTP = "auth/forgotPassword"
        const val RESET_PASSWORD = "auth/resetPassword"
        const val LOGOUT = "auth"
        const val USER_PROFILE = "me"
        const val CREATE_REPORT = "reports"
    }

    @POST(REGISTER_API)
    fun registerUser(@Body user: User): Observable<RegistrationResponse>

    @POST(LOGIN_API)
    fun loginUser(@Body loginRequest: LoginRequest): Observable<LoginResponse>

    @POST(SEND_RESET_PASSWORD_OTP)
    fun sendEmailResetOtp(): Completable

    @POST(RESET_PASSWORD)
    fun resetPassword(): Completable

    @DELETE(LOGOUT)
    fun logoutUser(): Completable

    @GET(USER_PROFILE)
    fun getUserProfile(): Observable<ProfileResponse>

    @Multipart
    @POST(CREATE_REPORT)
    fun createReport(
        @Part("state") state: RequestBody,
        @Part("lga") lga: RequestBody,
        @Part("category") category: RequestBody,
        @Part("title") title: RequestBody,
        @Part("is_anonymous") is_anonymous: RequestBody,
        @Part("message") message: RequestBody,
        @Part("medial") media: MultipartBody.Part?
    ): Observable<ResponseBody>
}