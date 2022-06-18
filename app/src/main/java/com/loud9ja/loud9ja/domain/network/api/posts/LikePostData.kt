package com.loud9ja.loud9ja.domain.network.api.posts


import com.google.gson.annotations.SerializedName

data class LikePostData(
    @SerializedName("total_dislikes")
    val totalDislikes: Int,
    @SerializedName("total_likes")
    val totalLikes: Int
)