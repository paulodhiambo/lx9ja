package com.loud9ja.loud9ja.ui.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityProfileBinding
import com.loud9ja.loud9ja.ui.home.HomeActivity
import com.loud9ja.loud9ja.utils.UIstate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    private var AUTOCOMPLETE_REQUEST_CODE = 1
    private var TAG = "ProfileActivity"
    private lateinit var dialog: Dialog
    private lateinit var mAuth: FirebaseAuth
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        viewModel.getUserProfile()
        observe()
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        val apiKey = "AIzaSyBr12zGXHf-2HjBjGhg8PUlv5ZawbNRvSE"
        if (!Places.isInitialized()) {
            Places.initialize(this, apiKey)
        }
        binding.logoView.bringToFront()
        binding.backArrow.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun observe() {
        viewModel.profileState.observe(this) { result ->
            when (result) {
                is UIstate.Success -> {
                    binding.profileName.text = "${result.data?.data?.name}"
                    binding.profileEmail.text = "${result.data?.data?.email}"
                    binding.profileGender.text = "${result.data?.data?.gender}"
                    binding.profileAge.text = "${result.data?.data?.age}"
                    binding.profileCountry.text = "${result.data?.data?.country}"
                    binding.profileCountr.text = "${result.data?.data?.country}"
                    binding.profilePhone.text = "${result.data?.data?.phone}"
                    binding.profileLocation.text = "${result.data?.data?.city}"
                    binding.btnEditProfile.setOnClickListener {
                        showDialog(
                            "${result.data?.data?.phone}",
                            "${result.data?.data?.city}",
                            "${result.data?.data?.email}"
                        )
                    }
                }
                is UIstate.Loading -> {}
                is UIstate.Error -> {
                    Log.d(TAG, "observe: ${result.message}")
                }
            }
        }
    }

    private fun showDialog(phoneText: String, locationText: String, emailText: String) {
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.edit_profile_dialog)
        val email = dialog.findViewById(R.id.email_address_input) as TextInputEditText
        email.setText(emailText)
        val phone = dialog.findViewById(R.id.phone_input) as TextInputEditText
        phone.setText(phoneText)
        val address = dialog.findViewById(R.id.location_input) as TextInputEditText
        address.setText(locationText)
        val saveButton = dialog.findViewById(R.id.save_btn) as Button
        val cancelButton = dialog.findViewById(R.id.cancel_btn) as Button
//        Glide.with(this)
//            .load(mAuth.currentUser?.photoUrl)
//            .into(binding.profileImage)
//        binding.profileName.text = mAuth.currentUser?.displayName
//        binding.profileEmail.text = mAuth.currentUser?.email
//        binding.profilePhone.text = mAuth.currentUser?.phoneNumber

        address.setOnClickListener {
            onPlaceSearched()
        }
        saveButton.setOnClickListener {
            dialog.dismiss()
        }
        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }

    private fun onPlaceSearched() {
        val fields: List<Place.Field> = mutableListOf(
            Place.Field.ID,
            Place.Field.NAME,
            Place.Field.ADDRESS,
            Place.Field.LAT_LNG
        )
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.FULLSCREEN, fields
        ).setCountry("NG") //NIGERIA
            .build(this)
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                RESULT_OK -> {
                    val place = Autocomplete.getPlaceFromIntent(data!!)
                    Log.i(TAG, "Place: " + place.name + ", " + place.id + ", " + place.address)
                    val name = place.name
                    dialog.findViewById<TextInputEditText>(R.id.location_input).setText(name)
                    // do query with address
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    val status: Status = Autocomplete.getStatusFromIntent(data!!)
                    status.statusMessage?.let { Log.i(TAG, it) }
                }
                RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
        }
    }
}