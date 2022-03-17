package com.loud9ja.loud9ja.domain.network.api.polls


import com.google.gson.annotations.SerializedName

data class Poll(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: String,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("ends_in")
    val endsIn: Int,
    @SerializedName("hide_creator_detail")
    val hideCreatorDetail: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_free")
    val isFree: String,
    @SerializedName("is_people_share")
    val isPeopleShare: Int,
    @SerializedName("options")
    val options: List<Option>,
    @SerializedName("profile_picture")
    val profilePicture: Any,
    @SerializedName("question")
    val question: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("view_count")
    val viewCount: Int
)