package com.loud9ja.loud9ja.domain.network.api.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("errors")
    val errors: List<String>,
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)