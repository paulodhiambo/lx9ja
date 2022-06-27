package com.loud9ja.loud9ja.ui.discusion

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentPostBinding
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
class PostFragment : BindingFragment<FragmentPostBinding>() {
    private val REQUEST_CODE = 101
    private val REQUEST_IMAGE_CAPTURE = 104
    private var imageFile: File? = null
    private val viewModel: DiscussionViewModel by viewModels()
    private var imageUri: MutableLiveData<Uri>? = null
    private var loadingBar: ProgressDialog? = null
    private val photo = MutableLiveData<String>()
    private var photoFile: File? = null

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPostBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingBar = ProgressDialog(requireContext())
        binding.button2.setOnClickListener {
            val title = binding.postTitle.text.toString()
            val body = binding.postBody.text.toString()
            createPost(title, body)
        }

        binding.attachment.setOnClickListener {
            captureImage()
        }
    }

    private fun createPost(title: String, description: String) {
        if (photo.value != null) {
            loadingBar?.setTitle("Creating Post")
            loadingBar?.setMessage("Please wait ... ")
            loadingBar?.setCancelable(false)
            loadingBar?.show()
            val file = File(photo.value!!)
            val groupName =
                title.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val groupDescription =
                description.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            viewModel.createPost(
                groupName,
                groupDescription,
                file,
                requireContext()
            )
            observeCreatePost()
        } else {
            requireActivity().showMessage("An attachment is required")
        }


    }

    private fun observeCreatePost() {
        viewModel.createPostsResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is UIstate.Success -> {
                    loadingBar?.dismiss()
                    requireActivity().showMessage("Post created successfully")
                    val fragment = DiscussionFragment()
                    val fragmentManager = fragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                    fragmentTransaction?.commit()
                }
                is UIstate.Loading -> {

                }
                is UIstate.Error -> {
                    loadingBar?.dismiss()
                }
            }
        }
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