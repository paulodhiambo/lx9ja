package com.loud9ja.loud9ja.stream.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Credentials(
    @SerializedName("key")
    @Expose
    val key: String, // aws-access-key
    @SerializedName("secret")
    @Expose
    val secret: String // aws-secret-key
)