package com.loud9ja.loud9ja.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.loud9ja.loud9ja.databinding.ActivitySpashBinding
import com.loud9ja.loud9ja.ui.calculator.CalculatorActivity
import com.loud9ja.loud9ja.ui.onboarding.GettingStartedActivity
import com.loud9ja.loud9ja.utils.AuthPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpashBinding
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = AuthPreference(this).getAuthDetails()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        Handler().postDelayed({
            if (user == null) {
                val intent = Intent(this, GettingStartedActivity::class.java)
                intent.apply {
                    this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, CalculatorActivity::class.java)
                intent.apply {
                    this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                finish()
            }

        }, 3000)

    }
}