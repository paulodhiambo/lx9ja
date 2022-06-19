package com.loud9ja.loud9ja.domain.network.api.groups


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Group(
    @SerializedName("access")
    @Expose
    val access: String, // PUBLIC
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-03-23T17:07:58.000000Z
    @SerializedName("deleted_at")
    @Expose
    val deletedAt: Any, // null
    @SerializedName("description")
    @Expose
    val description: String, // We’re
    @SerializedName("id")
    @Expose
    val id: Int, // 1
    @SerializedName("is_free")
    @Expose
    val isFree: String, // NO
    @SerializedName("media")
    @Expose
    val media: String, // /tmp/phpxk3AVj
    @SerializedName("name")
    @Expose
    val name: String, // iOS
    @SerializedName("total_members")
    @Expose
    val totalMembers: Int, // 2
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String, // 2022-03-23T17:07:58.000000Z
    @SerializedName("user_id")
    @Expose
    val userId: Int, // 1
    @SerializedName("view_count")
    @Expose
    val viewCount: Int // 0
) : Serializable