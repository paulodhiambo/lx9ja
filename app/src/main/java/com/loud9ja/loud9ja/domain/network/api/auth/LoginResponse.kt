package com.loud9ja.loud9ja.domain.network.api.auth


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class LoginResponse(
    @SerializedName("data")
    @Expose
    val `data`: List<Data?>?,
    @SerializedName("message")
    @Expose
    val message: String?, // We're glad to see you back on Loud9ja,Anna Kude
    @SerializedName("meta")
    @Expose
    val meta: String?, // valid
    @SerializedName("success")
    @Expose
    val success: String?, // true
    @SerializedName("token")
    @Expose
    val token: String? // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9waHBzdGFjay01Mzk3OTktMjczMTM3Mi5jbG91ZHdheXNhcHBzLmNvbVwvYXBpXC92MlwvYXV0aFwvbG9naW4iLCJpYXQiOjE2NTg0NjkzODQsIm5iZiI6MTY1ODQ2OTM4NCwianRpIjoiSW1FR1hCQjN2RzZOVDNpeCIsInN1YiI6MjYsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.SZf36Zs6bA2RtG0lQmZIZ9HMs8WCC4cup_GTgKxbCns
)