package com.loud9ja.loud9ja.domain.network.api.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RegistrationResponse(
    @SerializedName("message")
    @Expose
    val message: String // Registration successfull.
)