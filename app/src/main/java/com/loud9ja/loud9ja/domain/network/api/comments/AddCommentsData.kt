package com.loud9ja.loud9ja.domain.network.api.comments


import com.google.gson.annotations.SerializedName

data class AddCommentsData(
    @SerializedName("total_comments")
    val totalComments: Int,
    @SerializedName("total_dislikes")
    val totalDislikes: Int,
    @SerializedName("total_likes")
    val totalLikes: Int
)