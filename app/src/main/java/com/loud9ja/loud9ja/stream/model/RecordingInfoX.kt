package com.loud9ja.loud9ja.stream.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RecordingInfoX(
    @SerializedName("enabled")
    @Expose
    val enabled: Boolean, // true
    @SerializedName("upload_info")
    @Expose
    val uploadInfo: UploadInfo
)