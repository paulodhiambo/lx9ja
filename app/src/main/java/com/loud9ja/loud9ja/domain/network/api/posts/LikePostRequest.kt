package com.loud9ja.loud9ja.domain.network.api.posts


import com.google.gson.annotations.SerializedName

data class LikePostRequest(
    @SerializedName("post_id")
    val postId: Int,
    @SerializedName("status")
    val status: String
)