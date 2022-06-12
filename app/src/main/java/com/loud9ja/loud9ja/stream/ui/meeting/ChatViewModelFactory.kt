package com.loud9ja.loud9ja.stream.ui.meeting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loud9ja.loud9ja.stream.ui.meeting.chat.ChatViewModel
import live.hms.video.sdk.HMSSDK

class ChatViewModelFactory(
    private val hmsSdk: HMSSDK
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(hmsSdk) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }

}