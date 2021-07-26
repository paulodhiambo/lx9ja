package com.loud9ja.loud9ja.ui.dashboard

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.loud9ja.loud9ja.R

data class DashboardItems(
    val drawable: Int,
    val title: String,
    val body: String
) {
    companion object {
        @JvmStatic
        @BindingAdapter("android:imageUri")
        fun bindImage(view: View, imageDrawable: Int) {
            val image = view.findViewById<ImageView>(R.id.imageView)
            Glide.with(view)
                .load(imageDrawable)
                .thumbnail(Glide.with(view).load(R.drawable.megaphone))
                .into(image)
        }
    }
}
