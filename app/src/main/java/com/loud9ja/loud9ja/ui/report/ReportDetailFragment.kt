package com.loud9ja.loud9ja.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentReportDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ReportDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportDetailFragment : Fragment() {
    private var _binding: FragmentReportDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReportDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}