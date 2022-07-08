package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class VoteRequest(
    @SerializedName("poll_id")
    @Expose
    val pollId: Int, // 14
    @SerializedName("poll_option_id")
    @Expose
    val pollOptionId: Int // 11
)