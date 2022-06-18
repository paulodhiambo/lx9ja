package com.loud9ja.loud9ja.ui.group

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentNewGroupBinding
import com.loud9ja.loud9ja.utils.AuthPreference
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.UIstate
import com.loud9ja.loud9ja.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NewGroupFragment : BindingFragment<FragmentNewGroupBinding>() {
    private var visibility = mutableListOf("PUBLIC", "PRIVATE")
    private val REQUEST_CODE = 101
    private val REQUEST_IMAGE_CAPTURE = 104
    private var imageFile: File? = null
    private val viewModel: GroupViewModel by viewModels()
    private var bitmapImage: Bitmap? = null
    private var imageUri: MutableLiveData<Uri>? = null
    private var loadingBar: ProgressDialog? = null

    private val photo = MutableLiveData<String>()
    private var photoFile: File? = null
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentNewGroupBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val authPreference = AuthPreference(requireContext())
        loadingBar = ProgressDialog(requireContext())
        val user = authPreference.getAuthDetails()
        if (user != null) {
            binding.adminName.text = user.userName
        }

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, visibility)
        binding.visibilityTextView.setAdapter(adapter)

        binding.createGroup.setOnClickListener {
            val groupName = binding.txtGroupName.text.toString().trim()
            val description = binding.txtDescription.text.toString().trim()
            val visibilityStatus = binding.visibilityTextView.text.toString().trim()
            val accessType = binding.visibilityTextView.text.toString().trim()
            Log.d("============>", "onViewCreated: $groupName, $description, $visibilityStatus, $accessType")
//            createGroup(groupName, description, visibilityStatus)
        }

        binding.selectImage.setOnClickListener {
            openGalleryForImage()
        }

    }

    private fun createGroup(name: String, description: String, status: String) {
        loadingBar?.setTitle("Creating group")
        loadingBar?.setMessage("Please wait ... ")
        loadingBar?.setCancelable(false)
        loadingBar?.show()
        val file = File(photo.value!!)
        val groupName =
            "guvjbbvj".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val groupDescription =
            "vbjhnbjhnb".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val groupStatus = "PUBLIC".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val people = "[3]".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        viewModel.createGroup(
            groupName,
            groupDescription,
            groupStatus,
            people,
            file,
            requireContext()
        )
        observeResponse()
    }

    private fun observeResponse() {
        viewModel.createGroupResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is UIstate.Success -> {
                    loadingBar?.dismiss()
                    requireActivity().showMessage("Group created successfully")
                    val fragment = GroupsFragment()
                    val fragmentManager = fragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                    fragmentTransaction?.commit()
                }
                is UIstate.Loading -> {
                    //show loading
                }
                is UIstate.Error -> {
                    loadingBar?.dismiss()
                    Log.d("Error===>", "observeResponse: ${result.message}")
                    requireActivity().showMessage("An error occurred")
                }
            }
        }
    }

    private fun openGalleryForImage() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, REQUEST_CODE
        //        )
        captureImage()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageFile = data!!.data!!.path?.let { File(it) }
            Log.d("Result================>", "onActivityResult: $imageFile")
            // imageView.setImageURI(data?.data) // handle chosen image
            //  binding.selectImage.setText(data.data)
            imageUri?.value = data.data
            val bitmap = BitmapFactory.decodeFile(photoFile!!.absolutePath)
        }
    }


    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
        photo.value = image.absoluteFile.toString()
        return image
    }

    private fun captureImage() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(requireContext().packageManager) != null) {
                //create the file where the photo should go
                try {
                    photoFile = createImageFile()
                    Log.d("Paul", photoFile!!.absolutePath)
                    if (photoFile != null) {
                        val photoUri = FileProvider.getUriForFile(
                            requireContext(),
                            "live.hms.app2.provider",
                            photoFile!!
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                } catch (ex: Exception) {
                    Log.e("Error::", ex.localizedMessage!!)
                }
            } else {
                //
            }
        }
    }
}