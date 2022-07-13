package com.loud9ja.loud9ja.domain.network.api.trending


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("age")
    @Expose
    val age: Int, // 59
    @SerializedName("city")
    @Expose
    val city: Any, // null
    @SerializedName("country")
    @Expose
    val country: Any, // null
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-01-24T18:10:16.000000Z
    @SerializedName("deleted_at")
    @Expose
    val deletedAt: Any, // null
    @SerializedName("email")
    @Expose
    val email: String, // thenjagua@gmail.com
    @SerializedName("gender")
    @Expose
    val gender: String, // MALE
    @SerializedName("id")
    @Expose
    val id: Int, // 1
    @SerializedName("lga")
    @Expose
    val lga: Any, // null
    @SerializedName("name")
    @Expose
    val name: String, // Diddy
    @SerializedName("otp")
    @Expose
    val otp: String, // 1246
    @SerializedName("otp_sent_on")
    @Expose
    val otpSentOn: String, // 2022-06-12 07:29:21
    @SerializedName("phone")
    @Expose
    val phone: Any, // null
    @SerializedName("platform")
    @Expose
    val platform: String, // LOUD
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String, // profile/50021-1652287412.jpg
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
    val updatedAt: String, // 2022-06-12T19:29:21.000000Z
    @SerializedName("verified")
    @Expose
    val verified: Int // 0
) : Serializable