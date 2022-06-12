package com.loud9ja.loud9ja.stream.ui.meeting.participants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentParticipantsBinding
import com.loud9ja.loud9ja.stream.model.RoomDetails
import com.loud9ja.loud9ja.stream.ui.meeting.MeetingState
import com.loud9ja.loud9ja.stream.ui.meeting.MeetingViewModel
import com.loud9ja.loud9ja.stream.ui.meeting.MeetingViewModelFactory
import com.loud9ja.loud9ja.stream.util.ROOM_DETAILS
import com.loud9ja.loud9ja.stream.util.viewLifecycle
import live.hms.video.sdk.models.HMSPeer

class ParticipantsFragment : Fragment() {

  private var binding by viewLifecycle<FragmentParticipantsBinding>()
  private var alertDialog: AlertDialog? = null

  private val meetingViewModel: MeetingViewModel by activityViewModels {
    MeetingViewModelFactory(
      requireActivity().application,
      requireActivity().intent!!.extras!![ROOM_DETAILS] as RoomDetails
    )
  }

  lateinit var adapter: ParticipantsAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentParticipantsBinding.inflate(inflater, container, false)
    initViewModels()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter =
      ParticipantsAdapter(meetingViewModel.isAllowedToChangeRole(),
        meetingViewModel.isAllowedToRemovePeers(),
        meetingViewModel.isAllowedToMutePeers(),
        meetingViewModel.isAllowedToAskUnmutePeers(),
      this::onSheetClicked)
    initViews()
  }

  private fun initViews() {
    binding.participantCount.text = "0"
    binding.recyclerView.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = this@ParticipantsFragment.adapter
    }

    binding.textInputSearch.apply {
      addTextChangedListener { text ->
        val items = meetingViewModel
          .peers
          .filter { text.isNullOrEmpty() || it.name.contains(text.toString(), true) }
          .toTypedArray()
        adapter.setItems(items)
      }
    }
  }

  private fun onSheetClicked(peer : HMSPeer) {
    val action = ParticipantsFragmentDirections.actionParticipantsFragmentToBottomSheetRoleChange(peer.peerID, meetingViewModel.getAvailableRoles().map { it.name }.toTypedArray(), peer.name)
    findNavController().navigate(action)
  }

  private fun initViewModels() {
    meetingViewModel.peerLiveDate.observe(viewLifecycleOwner) {
      val peers = meetingViewModel.peers
      adapter.setItems(peers)
      binding.participantCount.text = "${peers.size}"
    }

    meetingViewModel.state.observe(viewLifecycleOwner) { state ->
      if(state is MeetingState.NonFatalFailure) {

          alertDialog?.dismiss()
          alertDialog = null

          val message = state.exception.message

          val builder = AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setTitle(R.string.non_fatal_error_dialog_title)
            .setCancelable(true)

          builder.setPositiveButton(R.string.ok) { dialog, _ ->
            dialog.dismiss()
            alertDialog = null
            meetingViewModel.setStatetoOngoing() // hack, so that the liveData represents the correct state. Use SingleLiveEvent instead
          }


          alertDialog = builder.create().apply { show() }
        }


    }
  }

}