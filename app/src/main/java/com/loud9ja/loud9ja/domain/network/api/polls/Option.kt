package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Option(
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-06-12T19:16:30.000000Z
    @SerializedName("deleted_at")
    @Expose
    val deletedAt: Any, // null
    @SerializedName("id")
    @Expose
    val id: Int, // 40
    @SerializedName("option")
    @Expose
    val option: String, // Pizza
    @SerializedName("poll_id")
    @Expose
    val pollId: Int, // 14
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String // 2022-06-12T19:16:30.000000Z
) : Serializable