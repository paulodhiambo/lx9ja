package com.loud9ja.loud9ja.stream.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CreateRoomRequest(
    @SerializedName("description")
    @Expose
    val description: String, // This is a test room
    @SerializedName("name")
    @Expose
    val name: String, // test-room
    @SerializedName("recording_info")
    @Expose
    val recordingInfo: RecordingInfoX
)