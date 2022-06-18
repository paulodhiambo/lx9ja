package com.loud9ja.loud9ja.ui.group

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentNewGroupBinding
import com.loud9ja.loud9ja.utils.AuthPreference
import com.loud9ja.loud9ja.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class NewGroupFragment : BindingFragment<FragmentNewGroupBinding>() {
    private var visibility = mutableListOf("PUBLIC", "PRIVATE")
    private val REQUEST_CODE = 101
    private var imageFile: File? = null
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentNewGroupBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val authPreference = AuthPreference(requireContext())
        val user = authPreference.getAuthDetails()
        if (user != null) {
            binding.adminName.text = user.userName
        }

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, visibility)
        binding.visibilityTextView.setAdapter(adapter)

        val groupName = binding.txtGroupName.text.toString().trim()
        val description = binding.txtDescription.text.toString().trim()
        val visibilityStatus = binding.visibilityTextView.text.toString().trim()
        Log.d("Result============>", "onViewCreated: ${groupName} $description ${visibilityStatus}")
        binding.createGroup.setOnClickListener {
            createGroup(groupName, description, visibilityStatus)
            Log.d("Result============>", "onViewCreated: ${imageFile?.path}")
//            if (groupName == "") {
//                requireActivity().showMessage("Input group name")
//
//            } else if (description == "") {
//                requireActivity().showMessage("Input group description")
//            } else if (visibilityStatus == "") {
//                requireActivity().showMessage("Select group visibility status")
//            } else if (imageFile == null) {
//                requireActivity().showMessage("Add group cover image")
//            } else {
//                createGroup(groupName, description, visibilityStatus)
//                Log.d("Result============>", "onViewCreated: ${imageFile?.path}")
//            }
        }

        binding.selectImage.setOnClickListener {
            openGalleryForImage()
        }

    }

    private fun createGroup(name: String, description: String, groupStatus: String) {

    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageFile = data!!.data!!.path?.let { File(it) }
            // imageView.setImageURI(data?.data) // handle chosen image
            //  binding.selectImage.setText(data.data)
        }
    }
}