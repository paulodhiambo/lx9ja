package com.loud9ja.loud9ja.ui.authentication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityLoginBinding
import com.loud9ja.loud9ja.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        setContentView(binding.root)
    }

    fun onLoginClick(view: View?) {
        startActivity(Intent(this, RegisterActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
    }

    fun onLoginSuccess(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.apply {
            this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
        finish()
    }
}