package com.loud9ja.loud9ja.domain.network.api.groups.new


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Data(
    @SerializedName("access")
    @Expose
    val access: String, // PUBLIC
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-06-18T07:43:28.000000Z
    @SerializedName("description")
    @Expose
    val description: String, // A health and nurtition channel in Uganda
    @SerializedName("id")
    @Expose
    val id: Int, // 11
    @SerializedName("media")
    @Expose
    val media: String, // group-icons/601787-1655538208.png
    @SerializedName("name")
    @Expose
    val name: String, // Testing Grouping
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String, // 2022-06-18T07:43:28.000000Z
    @SerializedName("user_id")
    @Expose
    val userId: Int // 31
)