package com.loud9ja.loud9ja.stream.ui.meeting.participants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.loud9ja.loud9ja.databinding.LayoutRtmpRecordingBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.loud9ja.loud9ja.stream.ui.meeting.MeetingViewModel
import com.loud9ja.loud9ja.stream.ui.meeting.RecordingTimesUseCase
import com.loud9ja.loud9ja.stream.ui.settings.SettingsStore
import com.loud9ja.loud9ja.stream.util.viewLifecycle
import live.hms.video.sdk.models.HMSHlsRecordingConfig
import java.net.URI
import java.net.URISyntaxException

class RtmpRecordFragment : Fragment() {

    private var binding by viewLifecycle<LayoutRtmpRecordingBinding>()
    private lateinit var settings: SettingsStore
    private val rtmpUrladapter: RtmpRecordAdapter = RtmpRecordAdapter(::removeItem)

    private val meetingViewModel: MeetingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = LayoutRtmpRecordingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings = SettingsStore(requireContext())
        // Get a listener on the page for the urls.

        binding.addRtmpUrlButton.setOnClickListener { addItem() }
        rtmpUrladapter.submitList(settings.rtmpUrlsList.toList())
        binding.rtmpUrls.layoutManager = LinearLayoutManager(context)
        binding.rtmpUrls.adapter = rtmpUrladapter
        binding.startButton.setOnClickListener { startClicked() }

        val recordingTimesUseCase = RecordingTimesUseCase()
        with(binding) {
            recording.text = recordingTimesUseCase.showRecordInfo(meetingViewModel.hmsSDK.getRoom()!!)
            rtmp.text = recordingTimesUseCase.showRtmpInfo(meetingViewModel.hmsSDK.getRoom()!!)
            sfu.text = recordingTimesUseCase.showServerInfo(meetingViewModel.hmsSDK.getRoom()!!)
            hlsSingleFilePerLayer.isEnabled = shouldStartHls.isChecked
            hlsVod.isEnabled = shouldStartHls.isChecked
            shouldStartHls.setOnCheckedChangeListener { buttonView, isChecked ->
                hlsSingleFilePerLayer.isEnabled = isChecked
                hlsVod.isEnabled = isChecked
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            meetingViewModel.events.collectLatest {
                when(it) {
                    is MeetingViewModel.Event.RecordEvent -> binding.recording.text = it.message
                    is MeetingViewModel.Event.ServerRecordEvent -> binding.sfu.text = it.message
                    is MeetingViewModel.Event.RtmpEvent -> binding.rtmp.text = it.message
                    else -> { /*Ignored*/ }
                }
                return@collectLatest
            }
        }
    }

    private fun addItem() {
        val urlToAdd = binding.newRtmpUrl.editableText.toString()
        if (urlToAdd.isEmpty()) {
            Toast.makeText(context, "Invalid url", Toast.LENGTH_LONG).show()
        } else if (!checkValidUrl(urlToAdd)) {
            Toast.makeText(context, "Invalid url, must start with RTMP", Toast.LENGTH_LONG)
                .show()
        } else {
            // URI is a valid rtmp url
            val newList = rtmpUrladapter.currentList.plus(urlToAdd)
            rtmpUrladapter.submitList(newList)
            settings.rtmpUrlsList = newList.toSet()
        }

    }

    private fun checkValidUrl(url: String): Boolean = try {
        val uri = URI(url)
        uri.scheme == "rtmp"
    } catch (e: URISyntaxException) {
        false
    }

    private fun startClicked() {
        // Create a config and start
        val isRecording = binding.shouldRecord.isChecked
        val isHls = binding.shouldStartHls.isChecked
        val meetingUrl = binding.meetingUrl.text.toString()
        val isRtmp = settings.rtmpUrlsList.toList().isNotEmpty()

        val isHlsSingleFilePerLayer = binding.hlsSingleFilePerLayer.isChecked
        val isHlsVod = binding.hlsVod.isChecked
        val hlsRecordingConfig = HMSHlsRecordingConfig(isHlsSingleFilePerLayer, isHlsVod)

        if (isRtmp && !isRecording && !isHls) {
            Toast.makeText(
                requireContext(),
                "Turn on recording, or provide rtmp urls, or start hls",
                Toast.LENGTH_LONG
            ).show()
        } else if (meetingUrl.isNullOrBlank()) {
            Toast.makeText(
                requireContext(),
                "A valid meeting url is required. $meetingUrl is invalid or not a role name",
                Toast.LENGTH_LONG
            ).show()
        } else if((isRecording || isRtmp) && isHls) {
            Toast.makeText(
                requireContext(),
                "Recording/Rtmp and HLS can't be started together. Only recording/rtmp streams can be started or just HLS.",
                Toast.LENGTH_LONG
            ).show()
        }
        else if(isRecording || isRtmp) {
            meetingViewModel.recordMeeting(isRecording, settings.rtmpUrlsList.toList(), meetingUrl)
            findNavController().popBackStack()
        } else if(isHls) {
            meetingViewModel.startHls(meetingUrl, hlsRecordingConfig)
            findNavController().popBackStack()
        }
    }

    private fun removeItem(url: String) {
        Log.d("rtmprecord", "clicked $url")
        // Remove this item from the adapter.
        val newList = rtmpUrladapter.currentList.minus(url)
        rtmpUrladapter.submitList(newList)
        // Actually remove it from preferences
        settings.rtmpUrlsList = newList.toSet()
    }
}