package com.loud9ja.loud9ja.domain.network.api.posts


import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("description")
    val description: String,
    @SerializedName("dislikes")
    val dislikes: Int,
    @SerializedName("group")
    val group: Any,
    @SerializedName("group_id")
    val groupId: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_free")
    val isFree: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("media")
    val media: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("view_count")
    val viewCount: Int
)