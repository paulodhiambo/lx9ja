package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PollResponseItem(
    @SerializedName("ends_in")
    @Expose
    val endsIn: String?, // 1
    @SerializedName("hide_creator_detail")
    @Expose
    val hideCreatorDetail: String?, // 1
    @SerializedName("id")
    @Expose
    val id: Int?, // 20
    @SerializedName("is_people_share")
    @Expose
    val isPeopleShare: String?, // 1
    @SerializedName("options")
    @Expose
    val options: List<Option?>?,
    @SerializedName("question")
    @Expose
    val question: String?, // 1
    @SerializedName("total_votes")
    @Expose
    val totalVotes: Int?, // 1
    @SerializedName("user")
    @Expose
    val user: User?
) : Serializable