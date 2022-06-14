package com.loud9ja.loud9ja.stream.ui.meeting

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loud9ja.loud9ja.stream.model.RoomDetails

class MeetingViewModelFactory(
  private val application: Application,
  private val roomDetails: RoomDetails
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(MeetingViewModel::class.java)) {
      return MeetingViewModel(application, roomDetails) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class $modelClass")
  }
}