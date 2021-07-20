package com.loud9ja.loud9ja.ui.home

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.loud9ja.loud9ja.databinding.FragmentReportBinding
import com.loud9ja.loud9ja.domain.Report
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ReportFragment : Fragment() {
    private var _binding: FragmentReportBinding? = null
    private val reportAdapter by lazy {
        ReportRecyclerViewAdapter()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reportsRecyclerview.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reportAdapter
            reportAdapter.addItems(
                mutableListOf(
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, false),
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, false),
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, true),
                    Report("", "", Calendar.getInstance().time, true)


                )
            )
        }
//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}