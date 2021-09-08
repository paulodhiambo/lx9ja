package com.loud9ja.loud9ja.network.api.request


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.loud9ja.loud9ja.data.Platform

data class LoginRequest(
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("password")
    @Expose
    val password: String,
    @SerializedName("platform")
    @Expose
    val platform: Platform
)