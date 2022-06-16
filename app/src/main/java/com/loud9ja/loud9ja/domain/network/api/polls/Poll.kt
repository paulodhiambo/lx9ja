package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Poll(
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-06-12T19:16:30.000000Z
    @SerializedName("created_by")
    @Expose
    val createdBy: String, // Anna Kude
    @SerializedName("deleted_at")
    @Expose
    val deletedAt: Any, // null
    @SerializedName("ending_at")
    @Expose
    val endingAt: String, // 2022-06-13 19:16:30
    @SerializedName("ends_in")
    @Expose
    val endsIn: Int, // 1
    @SerializedName("hide_creator_detail")
    @Expose
    val hideCreatorDetail: Int, // 1
    @SerializedName("id")
    @Expose
    val id: Int, // 14
    @SerializedName("is_free")
    @Expose
    val isFree: String, // NO
    @SerializedName("is_people_share")
    @Expose
    val isPeopleShare: Int, // 0
    @SerializedName("options")
    @Expose
    val options: List<Option>,
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String, // profile/default.png
    @SerializedName("question")
    @Expose
    val question: String, // What is your favourite meal?
    @SerializedName("selected_answers")
    @Expose
    val selectedAnswers: List<Any>,
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String, // 2022-06-12T19:16:30.000000Z
    @SerializedName("user_id")
    @Expose
    val userId: Int, // 29
    @SerializedName("view_count")
    @Expose
    val viewCount: Int // 0
)