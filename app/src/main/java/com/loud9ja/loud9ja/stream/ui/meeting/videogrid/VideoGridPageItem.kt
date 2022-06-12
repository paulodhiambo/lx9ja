package com.loud9ja.loud9ja.stream.ui.meeting.videogrid

import com.loud9ja.loud9ja.stream.ui.meeting.MeetingTrack

data class VideoGridPageItem(
  val id: Long,
  val items: Array<MeetingTrack>
) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as VideoGridPageItem

    if (id != other.id) return false
    if (!items.contentEquals(other.items)) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + items.contentHashCode()
    return result
  }
}