package com.loud9ja.loud9ja.domain.network.api.posts


import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("errors")
    val errors: List<Any>,
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: String,
    @SerializedName("success")
    val success: Boolean
)