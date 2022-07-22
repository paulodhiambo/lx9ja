package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

data class Option(
    @SerializedName("id")
    @Expose
    val id: Int?, // 22
    @SerializedName("option")
    @Expose
    val option: Any?, // null
    @SerializedName("total_vote")
    @Expose
    val totalVote: Int?, // 1
    @SerializedName("voting")
    @Expose
    val voting: Int? // 100
):Serializable