package com.loud9ja.loud9ja.domain.network.api.profile


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Data(
    @SerializedName("age")
    @Expose
    val age: Int, // 19
    @SerializedName("city")
    @Expose
    val city: Any, // null
    @SerializedName("country")
    @Expose
    val country: Any, // null
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-06-13T06:23:21.000000Z
    @SerializedName("deleted_at")
    @Expose
    val deletedAt: Any, // null
    @SerializedName("email")
    @Expose
    val email: String, // akudrre@gmail.com
    @SerializedName("gender")
    @Expose
    val gender: String, // MALE
    @SerializedName("id")
    @Expose
    val id: Int, // 31
    @SerializedName("lga")
    @Expose
    val lga: Any, // null
    @SerializedName("name")
    @Expose
    val name: String, // Anna Kude
    @SerializedName("otp")
    @Expose
    val otp: Any, // null
    @SerializedName("otp_sent_on")
    @Expose
    val otpSentOn: Any, // null
    @SerializedName("phone")
    @Expose
    val phone: Any, // null
    @SerializedName("platform")
    @Expose
    val platform: String, // LOUD
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String, // profile/default.png
    @SerializedName("provider_id")
    @Expose
    val providerId: Any, // null
    @SerializedName("provider_name")
    @Expose
    val providerName: Any, // null
    @SerializedName("role")
    @Expose
    val role: String, // CUSTOMER
    @SerializedName("state")
    @Expose
    val state: Any, // null
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String, // 2022-06-13T06:23:21.000000Z
    @SerializedName("verified")
    @Expose
    val verified: Int // 0
)