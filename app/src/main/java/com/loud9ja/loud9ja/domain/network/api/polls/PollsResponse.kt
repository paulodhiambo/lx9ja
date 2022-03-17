package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.SerializedName

data class PollsResponse(
    @SerializedName("data")
    val `data`: List<Poll>,
    @SerializedName("errors")
    val errors: List<String>,
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: String,
    @SerializedName("success")
    val success: Boolean
)