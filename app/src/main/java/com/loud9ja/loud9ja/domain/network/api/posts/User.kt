package com.loud9ja.loud9ja.domain.network.api.posts


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("age")
    val age: Int,
    @SerializedName("city")
    val city: Any,
    @SerializedName("country")
    val country: Any,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lga")
    val lga: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("otp")
    val otp: String,
    @SerializedName("otp_sent_on")
    val otpSentOn: String,
    @SerializedName("phone")
    val phone: Any,
    @SerializedName("platform")
    val platform: String,
    @SerializedName("profile_picture")
    val profilePicture: Any,
    @SerializedName("provider_id")
    val providerId: Any,
    @SerializedName("provider_name")
    val providerName: Any,
    @SerializedName("role")
    val role: String,
    @SerializedName("state")
    val state: Any,
    @SerializedName("updated_at")
    val updatedAt: String
)