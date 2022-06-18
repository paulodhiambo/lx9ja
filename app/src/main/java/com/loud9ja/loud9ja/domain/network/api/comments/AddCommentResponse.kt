package com.loud9ja.loud9ja.domain.network.api.comments


import com.google.gson.annotations.SerializedName

data class AddCommentResponse(
    @SerializedName("data")
    val addCommentsData: AddCommentsData,
    @SerializedName("errors")
    val errors: List<String>,
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: String,
    @SerializedName("success")
    val success: Boolean
)