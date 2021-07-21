package com.loud9ja.loud9ja.ui.report

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentNewReportBinding
import com.loud9ja.loud9ja.databinding.FragmentReportBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NewReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewReportFragment : Fragment() {
    private var _binding: FragmentNewReportBinding? = null
    private val binding get() = _binding!!
    val OPEN_DOCUMENT_REQUEST_CODE = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectFile.setOnClickListener {
            openDocumentPicker()
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