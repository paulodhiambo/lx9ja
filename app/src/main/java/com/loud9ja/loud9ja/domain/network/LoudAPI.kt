package com.loud9ja.loud9ja.domain.network

import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.domain.network.api.polls.PollsResponse
import com.loud9ja.loud9ja.domain.network.api.posts.PostResponse
import com.loud9ja.loud9ja.domain.network.api.request.LoginRequest
import com.loud9ja.loud9ja.domain.network.api.response.LoginResponse
import com.loud9ja.loud9ja.domain.network.api.response.ProfileResponse
import com.loud9ja.loud9ja.domain.network.api.response.RegistrationResponse
import com.loud9ja.loud9ja.domain.repository.PostsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface LoudAPI {
    companion object {
        const val REGISTER_API = "auth/register"
        const val LOGIN_API = "auth/login"
        const val SEND_RESET_PASSWORD_OTP = "auth/forgotPassword"
        const val RESET_PASSWORD = "auth/resetPassword"
        const val LOGOUT = "auth"
        const val USER_PROFILE = "me"
        const val CREATE_REPORT = "reports"
        const val GET_POSTS = "posts/list"
        const val GET_POLLS = "polls/list"
    }

    @GET(GET_POLLS)
    suspend fun getPolls(): PollsResponse

    @GET(GET_POSTS)
    suspend fun getPosts(): PostResponse

    @POST(REGISTER_API)
    suspend fun registerUser(@Body user: User): RegistrationResponse

    @POST(LOGIN_API)
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    @POST(SEND_RESET_PASSWORD_OTP)
    suspend fun sendEmailResetOtp(): ResponseBody

    @POST(RESET_PASSWORD)
    suspend fun resetPassword(): ResponseBody

    @DELETE(LOGOUT)
    suspend fun logoutUser(): ResponseBody

    @GET(USER_PROFILE)
    suspend fun getUserProfile(): ProfileResponse

    @Multipart
    @POST(CREATE_REPORT)
    suspend fun createReport(
        @Part("state") state: RequestBody,
        @Part("lga") lga: RequestBody,
        @Part("category") category: RequestBody,
        @Part("title") title: RequestBody,
        @Part("is_anonymous") is_anonymous: RequestBody,
        @Part("message") message: RequestBody,
        @Part("medial") media: MultipartBody.Part?
    ): ResponseBody
}