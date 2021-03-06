package com.loud9ja.loud9ja.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.data.Gender
import com.loud9ja.loud9ja.data.Platform
import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.databinding.ActivityRegisterBinding
import com.loud9ja.loud9ja.utils.DataState
import com.loud9ja.loud9ja.utils.Validator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var mAuth: FirebaseAuth
    private val authViewModel: AuthViewModel by viewModels()
    private var TAG = "RegisterActivity"
    private var gender = mutableListOf("Male", "Female", "Other")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cirRegisterButton.setOnClickListener {
            if (Patterns.EMAIL_ADDRESS.matcher(binding.editTextEmail.text.toString().trim())
                    .matches() && binding.editTextPassword.text.toString().trim().length >= 8
            ) {
                authViewModel.registerUser(registerUserData())
                observeRegistration()
            }

        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gender)
        binding.genderTextView.setAdapter(adapter)

        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun observeRegistration() {
        binding.cirRegisterButton.startAnimation()
        authViewModel.registrationResponse.observe(this) { data ->
            when (data) {
                is DataState.Success -> {
                    binding.cirRegisterButton.stopAnimation()
                    onSignUpSuccess()
                }
                is DataState.Loading -> {
                }
                is DataState.Error -> {
                    binding.cirRegisterButton.stopAnimation()
                }
            }
        }
    }

    private fun onSignUpSuccess() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        startActivity(intent)
        finish()
    }

    private fun registerUserData(): User {
        return User(
            binding.editTextName.text.toString().trim(),
            binding.editTextEmail.text.toString().trim(),
            binding.editTextPassword.text.toString().trim(),
            binding.editTextPassword.text.toString().trim(),
            21,
            Gender.MALE,
            Platform.LOUD,
        )
    }

    private fun validateInput(): Boolean {
        return (Validator.isValidEmail(binding.editTextEmail.text.toString().trim(), true)
                && Validator.isValidName(binding.editTextName.text.toString().trim(), true)
                && Validator.isValidPassword(binding.editTextPassword.text.toString().trim(), true))
    }

    private fun showMessageToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun onLoginClick(view: View?) {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right)
    }

}