package com.loud9ja.loud9ja.domain.network.api.trending


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class TrendingPostResponse(
    @SerializedName("data")
    @Expose
    val `data`: List<Data>,
    @SerializedName("errors")
    @Expose
    val errors: List<Any>,
    @SerializedName("message")
    @Expose
    val message: String, // Post retrieved successfully
    @SerializedName("meta")
    @Expose
    val meta: String, // success
    @SerializedName("success")
    @Expose
    val success: Boolean // true
)