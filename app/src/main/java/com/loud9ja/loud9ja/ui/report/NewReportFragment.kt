package com.loud9ja.loud9ja.ui.report

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentNewReportBinding
import com.loud9ja.loud9ja.databinding.FragmentReportBinding
import com.loud9ja.loud9ja.utils.BindingFragment


/**
 * A simple [Fragment] subclass.
 * Use the [NewReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewReportFragment : BindingFragment<FragmentNewReportBinding>() {
    val OPEN_DOCUMENT_REQUEST_CODE = 2
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentNewReportBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectFile.setOnClickListener {
            openDocumentPicker()
        }

        binding.btnSubmitReport.setOnClickListener {
            val fragment = ReportSuccessFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
            fragmentTransaction?.commit()
        }
    }

    private fun openDocumentPicker() {
        val openDocumentIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }

        startActivityForResult(openDocumentIntent, OPEN_DOCUMENT_REQUEST_CODE)
    }

}