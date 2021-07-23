package com.loud9ja.loud9ja.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentReportSuccessBinding
import com.loud9ja.loud9ja.ui.discusion.DiscussionFragment
import com.loud9ja.loud9ja.utils.BindingFragment


/**
 * A simple [Fragment] subclass.
 * Use the [ReportSuccessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportSuccessFragment : BindingFragment<FragmentReportSuccessBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentReportSuccessBinding::inflate

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