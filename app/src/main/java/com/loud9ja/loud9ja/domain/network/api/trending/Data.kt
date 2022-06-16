package com.loud9ja.loud9ja.domain.network.api.trending


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

data class Data(
    @SerializedName("comments")
    @Expose
    val comments: Int, // 3
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-03-23T17:10:32.000000Z
    @SerializedName("created_by")
    @Expose
    val createdBy: String, // Diddy
    @SerializedName("deleted_at")
    @Expose
    val deletedAt: Any, // null
    @SerializedName("description")
    @Expose
    val description: String, // SfdsfsdSFSDFSfs
    @SerializedName("group")
    @Expose
    val group: Any, // null
    @SerializedName("group_id")
    @Expose
    val groupId: Any, // null
    @SerializedName("id")
    @Expose
    val id: Int, // 1
    @SerializedName("is_free")
    @Expose
    val isFree: String, // NO
    @SerializedName("likes")
    @Expose
    val likes: Int, // 2
    @SerializedName("media")
    @Expose
    val media: String, // posts/1648055432.jpg
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String, // profile/50021-1652287412.jpg
    @SerializedName("title")
    @Expose
    val title: String, // iOS post
    @SerializedName("total_dislikes")
    @Expose
    val totalDislikes: Int, // 2
    @SerializedName("total_likes")
    @Expose
    val totalLikes: Int, // 2
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String, // 2022-03-23T17:10:32.000000Z
    @SerializedName("user")
    @Expose
    val user: User,
    @SerializedName("user_id")
    @Expose
    val userId: Int, // 1
    @SerializedName("view_count")
    @Expose
    val viewCount: Int // 0
):Serializable