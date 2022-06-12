package com.loud9ja.loud9ja.stream.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class UploadInfo(
    @SerializedName("credentials")
    @Expose
    val credentials: Credentials,
    @SerializedName("location")
    @Expose
    val location: String, // test-bucket
    @SerializedName("options")
    @Expose
    val options: Options,
    @SerializedName("prefix")
    @Expose
    val prefix: String, // test-prefix
    @SerializedName("type")
    @Expose
    val type: String // s3
)