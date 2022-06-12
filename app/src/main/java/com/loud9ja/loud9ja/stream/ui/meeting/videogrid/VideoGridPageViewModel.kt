package com.loud9ja.loud9ja.stream.ui.meeting.videogrid

import androidx.lifecycle.ViewModel
import com.loud9ja.loud9ja.stream.ui.meeting.MeetingTrack

class VideoGridPageViewModel : ViewModel() {
  var initialVideos = arrayOf<MeetingTrack>()
  var onVideoItemClick: ((MeetingTrack) -> Unit)? = null
}