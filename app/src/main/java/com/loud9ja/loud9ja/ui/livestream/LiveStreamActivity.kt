package com.loud9ja.loud9ja.ui.livestream

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityLiveStreamBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveStreamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLiveStreamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveStreamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val images: ArrayList<Any> = ArrayList()
        images.add(R.drawable.one)
        images.add(R.drawable.three)
        images.add(R.drawable.two)
        binding.stackProfiles.setImageLists(images)

    }
}