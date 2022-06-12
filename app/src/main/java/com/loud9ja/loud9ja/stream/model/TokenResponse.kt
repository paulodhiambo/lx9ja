package com.loud9ja.loud9ja.stream.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TokenResponse(
  @SerializedName("token") val token: String
)