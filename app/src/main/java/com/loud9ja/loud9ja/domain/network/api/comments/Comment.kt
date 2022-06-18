package com.loud9ja.loud9ja.domain.network.api.comments


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Comment(
    @SerializedName("comment")
    @Expose
    val comment: String, // 444
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-03-23T17:11:14.000000Z
    @SerializedName("created_by")
    @Expose
    val createdBy: String, // Diddy
    @SerializedName("deleted_at")
    @Expose
    val deletedAt: Any, // null
    @SerializedName("id")
    @Expose
    val id: Int, // 1
    @SerializedName("parent_id")
    @Expose
    val parentId: Any, // null
    @SerializedName("post_id")
    @Expose
    val postId: Int, // 1
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String, // profile/50021-1652287412.jpg
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String, // 2022-03-23T17:11:14.000000Z
    @SerializedName("user_id")
    @Expose
    val userId: Int // 1
)