package com.loud9ja.loud9ja.domain.network.api.registration


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RegistrationResponse(
    @SerializedName("data")
    @Expose
    val `data`: Data,
    @SerializedName("message")
    @Expose
    val message: String, // You have successfully registered on Loud9ja!.
    @SerializedName("meta")
    @Expose
    val meta: String, // valid
    @SerializedName("success")
    @Expose
    val success: Boolean // true
)