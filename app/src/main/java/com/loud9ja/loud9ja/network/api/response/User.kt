package com.loud9ja.loud9ja.network.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("age")
    @Expose
    val age: Int,
    @SerializedName("city")
    @Expose
    val city: Any,
    @SerializedName("country")
    @Expose
    val country: Any,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("gender")
    @Expose
    val gender: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("lga")
    @Expose
    val lga: Any,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("phone")
    @Expose
    val phone: Any,
    @SerializedName("profile_picture")
    @Expose
    val profile_picture: Any,
    @SerializedName("role")
    @Expose
    val role: String,
    @SerializedName("state")
    @Expose
    val state: Any
)