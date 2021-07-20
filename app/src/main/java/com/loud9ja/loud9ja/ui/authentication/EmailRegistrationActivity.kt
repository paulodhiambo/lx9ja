package com.loud9ja.loud9ja.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityEmailRegistrationBinding

class EmailRegistrationActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmailRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}