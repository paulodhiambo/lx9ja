package com.loud9ja.loud9ja.stream.ui.meeting.chat

import live.hms.video.sdk.models.HMSMessage
import java.util.*

data class ChatMessage(
    val senderName: String,
    val time: Date,
    val message: String,
    val isSentByMe: Boolean,
    val recipient: Recipient
) {
    constructor(message: HMSMessage, sentByMe: Boolean) : this(
        message.sender.name,
        message.serverReceiveTime,
        message.message,
        sentByMe,
        Recipient.toRecipient(message.recipient)
    )
}
