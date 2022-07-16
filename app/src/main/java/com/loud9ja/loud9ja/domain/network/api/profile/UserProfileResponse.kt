package com.loud9ja.loud9ja.domain.network.api.profile


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class UserProfileResponse(
    @SerializedName("data")
    @Expose
    val `data`: Data,
    @SerializedName("errors")
    @Expose
    val errors: List<Any>,
    @SerializedName("message")
    @Expose
    val message: String, // you have successfully retrieved you profile details
    @SerializedName("meta")
    @Expose
    val meta: String, // user
    @SerializedName("success")
    @Expose
    val success: Boolean // true
)