package com.loud9ja.loud9ja.domain.network.api.comments


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostCommentResponse(
    @SerializedName("errors")
    @Expose
    val errors: List<Any>,
    @SerializedName("message")
    @Expose
    val message: String, // Post retrieved successfully
    @SerializedName("meta")
    @Expose
    val meta: String, // success
    @SerializedName("data")
    val result: Result,
    @SerializedName("success")
    @Expose
    val success: Boolean // true
)
//http://loud9ja.travelinnovators.biz/api/v2/posts/list-comments/:1
//http://loud9ja.travelinnovators.biz/api/v2/posts/list-comments/:id