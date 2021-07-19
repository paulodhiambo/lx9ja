package com.loud9ja.loud9ja.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityGettingStartedBinding
import com.loud9ja.loud9ja.ui.authentication.LoginActivity

class GettingStartedActivity : AppCompatActivity() {
    lateinit var binding: ActivityGettingStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGettingStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnGettingStarted = binding.root.findViewById<Button>(R.id.btn_getting_started)
        btnGettingStarted.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}