package com.loud9ja.loud9ja.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentReportSuccessBinding
import com.loud9ja.loud9ja.ui.home.DiscussionFragment


/**
 * A simple [Fragment] subclass.
 * Use the [ReportSuccessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportSuccessFragment : Fragment() {
    private var _binding: FragmentReportSuccessBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReportSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBackHome.setOnClickListener {
            val fragment = DiscussionFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.commit()
        }
    }
}