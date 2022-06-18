package com.loud9ja.loud9ja.domain.network.api.posts


import com.google.gson.annotations.SerializedName

data class LikePostResponse(
    @SerializedName("data")
    val likePostData: LikePostData,
    @SerializedName("errors")
    val errors: List<String>,
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: String,
    @SerializedName("success")
    val success: Boolean
)