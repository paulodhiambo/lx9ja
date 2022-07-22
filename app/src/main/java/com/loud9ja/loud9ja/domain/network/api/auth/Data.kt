package com.loud9ja.loud9ja.domain.network.api.auth


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Data(
    @SerializedName("age")
    @Expose
    val age: String?, // 19
    @SerializedName("city")
    @Expose
    val city: String?,
    @SerializedName("country")
    @Expose
    val country: String?,
    @SerializedName("created_at")
    @Expose
    val createdAt: String?, // 2022-07-21T18:07:33.000000Z
    @SerializedName("email")
    @Expose
    val email: String?, // akudrre@gmail.com
    @SerializedName("gender")
    @Expose
    val gender: String?, // male
    @SerializedName("id")
    @Expose
    val id: Int?, // 26
    @SerializedName("lga")
    @Expose
    val lga: String?,
    @SerializedName("name")
    @Expose
    val name: String?, // Anna Kude
    @SerializedName("phone")
    @Expose
    val phone: String?,
    @SerializedName("platform")
    @Expose
    val platform: String?, // LOUD
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String?,
    @SerializedName("role")
    @Expose
    val role: String?, // user
    @SerializedName("state")
    @Expose
    val state: String?,
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String? // 2022-07-21T18:07:33.000000Z
)