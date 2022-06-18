package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class PollResponse(
    @SerializedName("data")
    @Expose
    val `data`: List<Poll>,
    @SerializedName("errors")
    @Expose
    val errors: List<String>,
    @SerializedName("message")
    @Expose
    val message: String, // You have successfully retrieved polls!
    @SerializedName("meta")
    @Expose
    val meta: String, // success
    @SerializedName("success")
    @Expose
    val success: Boolean // true
)