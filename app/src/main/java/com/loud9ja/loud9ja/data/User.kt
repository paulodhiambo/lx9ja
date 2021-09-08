package com.loud9ja.loud9ja.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("email")
    @Expose
    var email: String?,
    @SerializedName("password")
    @Expose
    var password: String?,
    @SerializedName("password_confirmation")
    @Expose
    var password_confirmation: String?,
    @SerializedName("age")
    @Expose
    var age: Int,
    @SerializedName("gender")
    @Expose
    var gender: Gender,
    @SerializedName("platform")
    @Expose
    var platform: Platform?
)