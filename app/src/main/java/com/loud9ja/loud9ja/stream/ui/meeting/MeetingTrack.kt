package com.loud9ja.loud9ja.stream.ui.meeting

import live.hms.video.media.tracks.HMSAudioTrack
import live.hms.video.media.tracks.HMSTrackSource
import live.hms.video.media.tracks.HMSVideoTrack
import live.hms.video.sdk.models.HMSPeer

data class MeetingTrack(
    val peer: HMSPeer,
    var video: HMSVideoTrack?,
    var audio: HMSAudioTrack?,
) {

    override fun equals(other: Any?): Boolean {
        if (other is MeetingTrack) {
            return (other.peer.peerID == peer.peerID && other.video?.trackId == video?.trackId && other.audio?.trackId == audio?.trackId)
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = peer.hashCode()
        result = 31 * result + (video?.hashCode() ?: 0)
        result = 31 * result + (audio?.hashCode() ?: 0)
        result = 31 * result + isLocal.hashCode()
        return result
    }

    private val isLocal: Boolean = peer.isLocal
    val isScreen: Boolean
        get() = video?.source == HMSTrackSource.SCREEN || video?.source == "videoplaylist"
}
