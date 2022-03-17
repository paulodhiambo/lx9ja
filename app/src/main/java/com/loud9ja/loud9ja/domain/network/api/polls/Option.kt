package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("option")
    val option: String,
    @SerializedName("poll_id")
    val pollId: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)