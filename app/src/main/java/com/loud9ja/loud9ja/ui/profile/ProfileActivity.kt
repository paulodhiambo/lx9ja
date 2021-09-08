package com.loud9ja.loud9ja.ui.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    private var AUTOCOMPLETE_REQUEST_CODE = 1
    private var TAG = "ProfileActivity"
    private lateinit var dialog: Dialog
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        val apiKey = "AIzaSyBr12zGXHf-2HjBjGhg8PUlv5ZawbNRvSE"
        if (!Places.isInitialized()) {
            Places.initialize(this, apiKey)
        }
        binding.logoView.bringToFront()
        binding.btnEditProfile.setOnClickListener {
            showDialog("+266 255 225", "Ikeja", "william@gmail.com")
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
        Glide.with(this)
            .load(mAuth.currentUser?.photoUrl)
            .into(binding.profileImage)
        binding.profileName.text = mAuth.currentUser?.displayName
        binding.profileEmail.text = mAuth.currentUser?.email
        binding.profilePhone.text = mAuth.currentUser?.phoneNumber

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