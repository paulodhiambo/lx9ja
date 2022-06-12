package com.loud9ja.loud9ja.stream.ui.meeting.audiomode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.loud9ja.loud9ja.databinding.FragmentAudioBinding
import com.loud9ja.loud9ja.stream.model.RoomDetails
import com.loud9ja.loud9ja.stream.ui.meeting.MeetingViewModel
import com.loud9ja.loud9ja.stream.ui.meeting.MeetingViewModelFactory
import com.loud9ja.loud9ja.stream.util.ROOM_DETAILS
import com.loud9ja.loud9ja.stream.util.viewLifecycle

class AudioModeFragment : Fragment() {

  private val adapter = AudioCollectionAdapter()
  private var binding by viewLifecycle<FragmentAudioBinding>()

  private val meetingViewModel: MeetingViewModel by activityViewModels {
    MeetingViewModelFactory(
      requireActivity().application,
      requireActivity().intent!!.extras!![ROOM_DETAILS] as RoomDetails
    )
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentAudioBinding.inflate(inflater, container, false)
    initViews()
    initViewModels()
    return binding.root
  }

  private fun initViews() {
    binding.recyclerView.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = this@AudioModeFragment.adapter
    }
  }

  private fun initViewModels() {
    meetingViewModel.tracks.observe(viewLifecycleOwner) {
      adapter.setItems(meetingViewModel.peers)
    }

    meetingViewModel.speakers.observe(viewLifecycleOwner) {
      adapter.applySpeakerUpdates(it)
    }

    if (meetingViewModel.isLocalVideoEnabled.value != false) {
      meetingViewModel.toggleLocalVideo()
    }
  }
}