package com.loud9ja.loud9ja.stream.ui.meeting.activespeaker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.loud9ja.loud9ja.databinding.HlsFragmentLayoutBinding
import com.loud9ja.loud9ja.stream.ui.meeting.HlsPlayer
import com.loud9ja.loud9ja.stream.util.viewLifecycle

class HlsFragment : Fragment() {

    private val args: HlsFragmentArgs by navArgs()

    private var binding by viewLifecycle<HlsFragmentLayoutBinding>()
    private val hlsPlayer : HlsPlayer by lazy{
        HlsPlayer()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HlsFragmentLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.hlsView.player = hlsPlayer.getPlayer(requireContext(),
                args.hlsStreamUrl,
                true)
    }

    override fun onStop() {
        super.onStop()
        hlsPlayer.releasePlayer()
    }
}