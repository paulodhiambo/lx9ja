package com.loud9ja.loud9ja.utils

import android.app.Activity
import android.widget.Toast

fun Activity.showMessage(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}