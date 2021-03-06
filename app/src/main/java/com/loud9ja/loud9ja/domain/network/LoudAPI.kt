package com.loud9ja.loud9ja.domain.network

import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.domain.firebase.poll.CreatePollRequest
import com.loud9ja.loud9ja.domain.network.api.auth.LoginResponse
import com.loud9ja.loud9ja.domain.network.api.comments.AddCommentRequest
import com.loud9ja.loud9ja.domain.network.api.comments.AddCommentResponse
import com.loud9ja.loud9ja.domain.network.api.comments.PostCommentsResponse
import com.loud9ja.loud9ja.domain.network.api.groups.GroupResponse
import com.loud9ja.loud9ja.domain.network.api.groups.response.CreateGroupResponse
import com.loud9ja.loud9ja.domain.network.api.polls.PollResponse
import com.loud9ja.loud9ja.domain.network.api.polls.VoteRequest
import com.loud9ja.loud9ja.domain.network.api.posts.LikePostRequest
import com.loud9ja.loud9ja.domain.network.api.posts.LikePostResponse
import com.loud9ja.loud9ja.domain.network.api.posts.PostResponse
import com.loud9ja.loud9ja.domain.network.api.profile.UserProfileResponse
import com.loud9ja.loud9ja.domain.network.api.registration.RegistrationResponse
import com.loud9ja.loud9ja.domain.network.api.reports.ReportResponse
import com.loud9ja.loud9ja.domain.network.api.request.LoginRequest
import com.loud9ja.loud9ja.domain.network.api.response.ProfileResponse
import com.loud9ja.loud9ja.domain.network.api.trending.TrendingPostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface LoudAPI {
    companion object {
        const val REGISTER_API = "api/v2/auth/register"
        const val LOGIN_API = "api/v2/auth/login"
        const val SEND_RESET_PASSWORD_OTP = "api/v2/auth/forgotPassword"
        const val RESET_PASSWORD = "api/v2/auth/resetPassword"
        const val LOGOUT = "api/v2/auth"
        const val USER_PROFILE = "api/v2/me"
        const val CREATE_REPORT = "api/v2/reports"
        const val GET_POSTS = "api/v2/posts/list"
        const val GET_POST_COMMENTS = "api/v2/posts/list-comments/{id}"
        const val ADD_POST_COMMENTS = "api/v2/posts/comment"
        const val GET_POLLS = "api/v2/polls/list"
        const val GET_GROUP = "api/v2/groups/list"
        const val GET_ALL_REPORTS = "api/v2/reports/list?id=2"
        const val TRENDING_POST = "api/v2/posts/trending"
        const val CREATE_GROUP = "api/v2/groups/create"
        const val LIKE_POST = "api/v2/posts/like"
        const val CREATE_POST = "api/v2/posts/create"
        const val VOTE = "api/v2/polls/vote"
        const val MY_PROFILE = "api/v2/users/my-profile"
        const val CREATE_POLL = "api/v2/polls/create"


    }

    @POST(CREATE_POLL)
    suspend fun createPoll(@Body poll: CreatePollRequest): ResponseBody

    @GET(MY_PROFILE)
    suspend fun profile(): UserProfileResponse

    @POST(VOTE)
    suspend fun vote(@Body vote: VoteRequest): ResponseBody

    @POST(TRENDING_POST)
    suspend fun getTrendingPosts(): TrendingPostResponse

    @GET(GET_ALL_REPORTS)
    suspend fun getReports(): ReportResponse

    @GET(GET_GROUP)
    suspend fun getGroups(): GroupResponse

    @GET(GET_POLLS)
    suspend fun getPolls(): PollResponse

    @GET(GET_POSTS)
    suspend fun getPosts(): PostResponse

    @GET(GET_POST_COMMENTS)
    suspend fun getPostComments(@Path("id") id: Int): PostCommentsResponse

    @POST(ADD_POST_COMMENTS)
    suspend fun addPostComments(@Body addCommentRequest: AddCommentRequest): AddCommentResponse

    @POST(LIKE_POST)
    suspend fun likePost(@Body likePostRequest: LikePostRequest): LikePostResponse

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
        @Part media: MultipartBody.Part?
    ): ResponseBody

    @Multipart
    @POST(CREATE_GROUP)
    suspend fun createGroup(
        @Part("name") state: RequestBody,
        @Part("description") lga: RequestBody,
        @Part("access") category: RequestBody,
        @Part("invited_people") title: RequestBody,
        @Part media: MultipartBody.Part?
    ): CreateGroupResponse

    @Multipart
    @POST(CREATE_POST)
    suspend fun createPost(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part media: MultipartBody.Part?
    ): ResponseBody
}