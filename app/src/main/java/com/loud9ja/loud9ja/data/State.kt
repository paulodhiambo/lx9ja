package com.loud9ja.loud9ja.data


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("alias")
    @Expose
    val alias: String, // abia
    @SerializedName("lgas")
    @Expose
    val lgas: List<String>,
    @SerializedName("state")
    @Expose
    val state: String // Abia
)