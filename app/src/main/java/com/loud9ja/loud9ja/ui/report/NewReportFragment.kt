package com.loud9ja.loud9ja.ui.report

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.google.gson.GsonBuilder
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.data.State
import com.loud9ja.loud9ja.databinding.FragmentNewReportBinding
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.*

@AndroidEntryPoint
class NewReportFragment : BindingFragment<FragmentNewReportBinding>() {
    val OPEN_DOCUMENT_REQUEST_CODE = 2
    private val statesList = ArrayList<String>()
    private val lgaList = ArrayList<String>()

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

    private fun loadJSONFromAsset(): String? {
        val json: String = try {
            val `is`: InputStream =
                Objects.requireNonNull(requireActivity()).assets.open("cars.json")
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun jsonToPojo(): Array<State>? {
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        val states = gson.fromJson(loadJSONFromAsset(), Array<State>::class.java)
        for (state in states) {
            statesList.add(state.state)
        }
        return states
    }
}