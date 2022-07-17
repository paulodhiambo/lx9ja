package com.loud9ja.loud9ja.domain.firebase.stream

data class LiveStream(
    val userImage: String = "",
    val userName: String = "",
    val streamId: String = "",
    val streamTitle: String = "",
    val startedAt: String = "",
    val ended: Boolean = false
)