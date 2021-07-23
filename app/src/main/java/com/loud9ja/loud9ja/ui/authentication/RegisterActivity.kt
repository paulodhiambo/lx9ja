package com.loud9ja.loud9ja.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityRegisterBinding
import com.loud9ja.loud9ja.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnEmailRegistration.setOnClickListener {
            startActivity(Intent(this, EmailRegistrationActivity::class.java))
        }
    }
}