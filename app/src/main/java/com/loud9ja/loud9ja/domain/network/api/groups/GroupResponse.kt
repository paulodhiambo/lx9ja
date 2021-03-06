package com.loud9ja.loud9ja.domain.network.api.groups


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("data")
    @Expose
    val `data`: Data,
    @SerializedName("errors")
    @Expose
    val errors: List<Any>,
    @SerializedName("message")
    @Expose
    val message: String, // Groups retrieved successfully
    @SerializedName("meta")
    @Expose
    val meta: String, // success
    @SerializedName("success")
    @Expose
    val success: Boolean // true
)