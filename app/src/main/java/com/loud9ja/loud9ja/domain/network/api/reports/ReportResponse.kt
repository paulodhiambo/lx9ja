package com.loud9ja.loud9ja.domain.network.api.reports


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ReportResponse(
    @SerializedName("data")
    @Expose
    val `data`: List<Data>,
    @SerializedName("errors")
    @Expose
    val errors: List<String>,
    @SerializedName("message")
    @Expose
    val message: String, // You have successfully retrieved reports
    @SerializedName("meta")
    @Expose
    val meta: String, // reports
    @SerializedName("success")
    @Expose
    val success: Boolean // true
)