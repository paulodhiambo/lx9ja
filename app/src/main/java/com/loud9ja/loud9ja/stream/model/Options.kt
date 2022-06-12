package com.loud9ja.loud9ja.stream.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Options(
    @SerializedName("region")
    @Expose
    val region: String // ap-south-1
)