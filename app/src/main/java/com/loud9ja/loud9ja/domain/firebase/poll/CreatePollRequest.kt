package com.loud9ja.loud9ja.domain.firebase.poll


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CreatePollRequest(
    @SerializedName("ends_in")
    @Expose
    val endsIn: Int, // 1
    @SerializedName("hide_creator_detail")
    @Expose
    val hideCreatorDetail: Int, // 1
    @SerializedName("is_people_share")
    @Expose
    val isPeopleShare: Int, // 0
    @SerializedName("options")
    @Expose
    val options: List<String>,
    @SerializedName("question")
    @Expose
    val question: String, // What is your favourite meal?
    @SerializedName("time")
    @Expose
    val time: String,
    @SerializedName("userimage")
    @Expose
    val userimage: String,
    @SerializedName("username")
    @Expose
    val username: String
)