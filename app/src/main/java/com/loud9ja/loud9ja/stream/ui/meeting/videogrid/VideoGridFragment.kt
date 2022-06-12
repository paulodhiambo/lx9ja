package com.loud9ja.loud9ja.stream.ui.meeting.videogrid

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.loud9ja.loud9ja.databinding.FragmentGridVideoBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.stream.model.RoomDetails
import com.loud9ja.loud9ja.stream.ui.meeting.MeetingViewModel
import com.loud9ja.loud9ja.stream.ui.meeting.MeetingViewModelFactory
import com.loud9ja.loud9ja.stream.ui.settings.SettingsStore
import com.loud9ja.loud9ja.stream.util.ROOM_DETAILS
import com.loud9ja.loud9ja.stream.util.viewLifecycle

class VideoGridFragment : Fragment() {
  companion object {
    private const val TAG = "VideoGridFragment"
  }

  private var binding by viewLifecycle<FragmentGridVideoBinding>()
  private lateinit var settings: SettingsStore

  private lateinit var clipboard: ClipboardManager

  private val meetingViewModel: MeetingViewModel by activityViewModels {
    MeetingViewModelFactory(
      requireActivity().application,
      requireActivity().intent!!.extras!![ROOM_DETAILS] as RoomDetails
    )
  }

  private lateinit var adapter: VideoGridAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    clipboard = requireActivity()
      .getSystemService(Context.CLIPBOARD_SERVICE)
        as ClipboardManager
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentGridVideoBinding.inflate(inflater, container, false)
    settings = SettingsStore(requireContext())

    initVideoGrid()
    initViewModels()
    return binding.root
  }

  private fun initVideoGrid() {
    adapter = VideoGridAdapter(this@VideoGridFragment) /* { video ->
      Log.v(TAG, "onVideoItemClick: $video")

      Snackbar.make(
          binding.root,
          "Name: ${video.peer.userName} (${video.peer.role}) \nId: ${video.peer.customerUserId}",
          Snackbar.LENGTH_LONG,
      ).setAction("Copy") {
        val clip = ClipData.newPlainText("Customer Id", video.peer.customerUserId)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(
            requireContext(),
            "Copied customer id of ${video.peer.userName} to clipboard",
            Toast.LENGTH_SHORT
        ).show()
      }.show()
    } */

    binding.viewPagerVideoGrid.apply {
      offscreenPageLimit = 1
      adapter = this@VideoGridFragment.adapter

      TabLayoutMediator(binding.tabLayoutDots, this) { _, _ ->
        // No text to be shown
      }.attach()
    }
  }

  @SuppressLint("SetTextI18n")
  private fun initViewModels() {
    meetingViewModel.tracks.observe(viewLifecycleOwner) { tracks ->
      val itemsPerPage = settings.videoGridRows * settings.videoGridColumns
      adapter.totalPages = (tracks.size + itemsPerPage - 1) / itemsPerPage
    }

    if (settings.detectDominantSpeaker) {
      meetingViewModel.dominantSpeaker.observe(viewLifecycleOwner) { meetingTrack ->
        if (meetingTrack == null) {
          binding.dominantSpeakerName.setText(R.string.no_one_speaking)
        } else {
          binding.dominantSpeakerName.text = "Dominant Speaker: ${meetingTrack.peer.name}"
        }
      }
    } else {
      binding.containerDominantSpeaker.visibility = View.GONE
    }
    binding.containerNetworkInfo.visibility = View.GONE
  }
}