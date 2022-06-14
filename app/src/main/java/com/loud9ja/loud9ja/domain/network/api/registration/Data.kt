package com.loud9ja.loud9ja.domain.network.api.registration


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Data(
    @SerializedName("age")
    @Expose
    val age: String, // 19
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-06-13T06:23:21.000000Z
    @SerializedName("email")
    @Expose
    val email: String, // akudrre@gmail.com
    @SerializedName("gender")
    @Expose
    val gender: String, // male
    @SerializedName("id")
    @Expose
    val id: Int, // 31
    @SerializedName("name")
    @Expose
    val name: String, // Anna Kude
    @SerializedName("platform")
    @Expose
    val platform: String, // LOUD
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String, // profile/default.png
    @SerializedName("role")
    @Expose
    val role: String, // CUSTOMER
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String // 2022-06-13T06:23:21.000000Z
)