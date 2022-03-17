package com.loud9ja.loud9ja.domain.network.api.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("age")
    @Expose
    val age: Int?, // 21
    @SerializedName("city")
    @Expose
    val city: String?,
    @SerializedName("country")
    @Expose
    val country: String?,
    @SerializedName("email")
    @Expose
    val email: String?, // it21@gmail.com
    @SerializedName("gender")
    @Expose
    val gender: String?, // MALE
    @SerializedName("id")
    @Expose
    val id: Int?, // 8
    @SerializedName("lga")
    @Expose
    val lga: String?,
    @SerializedName("name")
    @Expose
    val name: String?, // Test
    @SerializedName("phone")
    @Expose
    val phone: String?,
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String?,
    @SerializedName("role")
    @Expose
    val role: String?, // CUSTOMER
    @SerializedName("state")
    @Expose
    val state: String?
)