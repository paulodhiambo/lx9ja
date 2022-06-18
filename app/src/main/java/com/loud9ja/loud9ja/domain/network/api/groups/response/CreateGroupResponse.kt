package com.loud9ja.loud9ja.domain.network.api.groups.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CreateGroupResponse(
    @SerializedName("data")
    @Expose
    val `data`: Data,
    @SerializedName("errors")
    @Expose
    val errors: List<Any>,
    @SerializedName("message")
    @Expose
    val message: String, // Group updated successfully.
    @SerializedName("meta")
    @Expose
    val meta: String, // success
    @SerializedName("success")
    @Expose
    val success: Boolean // true
)