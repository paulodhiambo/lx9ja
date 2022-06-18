package com.loud9ja.loud9ja.domain.network.api.comments


import com.google.gson.annotations.SerializedName

data class AddCommentRequest(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("post_id")
    val postId: Int
)