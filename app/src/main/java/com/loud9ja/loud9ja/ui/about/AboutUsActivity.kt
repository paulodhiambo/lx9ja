package com.loud9ja.loud9ja.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.loud9ja.loud9ja.databinding.ActivityAboutUsBinding
import com.loud9ja.loud9ja.ui.home.HomeActivity

class AboutUsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backArrow.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.apply {
                this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }

        binding.contactUs.setOnClickListener {
            val uri = "https://api.whatsapp.com/send?phone=+2348097175974"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            startActivity(intent)
        }
    }
}