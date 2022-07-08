package com.loud9ja.loud9ja.stream.ui.meeting

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.loud9ja.loud9ja.databinding.ActivityMeetingBinding
import com.loud9ja.loud9ja.stream.model.RoomDetails
import com.loud9ja.loud9ja.stream.util.ROOM_DETAILS

class MeetingActivity : AppCompatActivity() {

    private var _binding: ActivityMeetingBinding? = null

    private val binding: ActivityMeetingBinding
        get() = _binding!!

    private val meetingViewModel: MeetingViewModel by viewModels {
        MeetingViewModelFactory(
            application,
            intent!!.extras!![ROOM_DETAILS] as RoomDetails
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMeetingBinding.inflate(layoutInflater)

        setContentView(binding.root)
//    setSupportActionBar(binding.containerToolbar.toolbar)
//    supportActionBar?.setDisplayShowTitleEnabled(false)

        initViewModels()

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.containerToolbar.container.visibility = View.GONE
        } else {
            binding.containerToolbar.container.visibility = View.VISIBLE
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

//  @SuppressLint("RestrictedApi")
//  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//    menuInflater.inflate(R.menu.menu_meeting, menu)
//
//    if (menu is MenuBuilder) {
//      menu.setOptionalIconsVisible(true)
//    }
//
//    return true
//  }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViewModels() {
        meetingViewModel.title.observe(this) {
            binding.containerToolbar.toolbar.setTitle(it)
        }
    }
}
