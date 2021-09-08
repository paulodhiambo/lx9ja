package com.loud9ja.loud9ja.network.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    @Expose
    val access_token: String,
    @SerializedName("token_type")
    @Expose
    val token_type: String,
    @SerializedName("user")
    @Expose
    val user: User
)