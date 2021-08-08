package com.loud9ja.loud9ja.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentSosBinding
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [SosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SosFragment : BindingFragment<FragmentSosBinding>() {
    private lateinit var bottomSheetDialog: BottomSheetDialog
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSosBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val distressCall = binding.imageDistress
        val safetyService = binding.imageSafety
        val policeCall = binding.imageViewPolice
        val medicalCall = binding.imageViewMedical
        val ndlae = binding.imageViewNd
        val nafdac = binding.imageView

        bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.police_bottom_sheet_dialog)

        distressCall.setOnClickListener {
            showBottomSheetDialog("Distress call")
        }
        safetyService.setOnClickListener {
            showBottomSheetDialog("Safety services call")
        }
        policeCall.setOnClickListener {
            showBottomSheetDialog("Nigeria police call")
        }
        medicalCall.setOnClickListener {
            showBottomSheetDialog("Medical service call")
        }
        ndlae.setOnClickListener {
            showBottomSheetDialog("NDLEA call")
        }
        nafdac.setOnClickListener {
            showBottomSheetDialog("NAFDAC call")
        }
    }


    private fun showBottomSheetDialog(title: String) {
        val titleText = bottomSheetDialog.findViewById<TextView>(R.id.title_text)
        titleText!!.text = title
        bottomSheetDialog.show()
    }
}