package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("age")
    @Expose
    val age: String?,
    @SerializedName("city")
    @Expose
    val city: Any?, // null
    @SerializedName("country")
    @Expose
    val country: Any?, // null
    @SerializedName("created_at")
    @Expose
    val createdAt: String?, // 2022-07-08T09:57:25.000000Z
    @SerializedName("email")
    @Expose
    val email: String?, // saad@mail2.com
    @SerializedName("gender")
    @Expose
    val gender: String?, // male
    @SerializedName("id")
    @Expose
    val id: Int?, // 10
    @SerializedName("lga")
    @Expose
    val lga: Any?, // null
    @SerializedName("name")
    @Expose
    val name: String?, // Muhammad
    @SerializedName("phone")
    @Expose
    val phone: Any?, // null
    @SerializedName("platform")
    @Expose
    val platform: String?, // Muhammad
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String?,
    @SerializedName("role")
    @Expose
    val role: String?, // user
    @SerializedName("state")
    @Expose
    val state: Any?, // null
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String? // 2022-07-13T07:37:07.000000Z
) : Serializable