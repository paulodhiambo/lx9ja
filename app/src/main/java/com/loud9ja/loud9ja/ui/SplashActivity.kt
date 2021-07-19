package com.loud9ja.loud9ja.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivitySpashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this, GettingStartedActivity::class.java))
        }, 3000)

    }
}