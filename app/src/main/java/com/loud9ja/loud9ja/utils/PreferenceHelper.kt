package com.loud9ja.loud9ja.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import com.loud9ja.loud9ja.R

class PreferenceHelper(val activity: Activity) {
    private val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

    fun saveTheme(theme: Int) {
        with(sharedPref.edit()) {
            putInt(activity.resources.getString(R.string.themes_key), theme)
            apply()
        }
    }

    fun getTheme(): Int {
        return sharedPref.getInt(activity.resources.getString(R.string.themes_key), 1)
    }

    fun saveFlow(opened: Int) {
        with(sharedPref.edit()) {
            putInt(activity.resources.getString(R.string.opned_key), opened)
            apply()
        }
    }

    fun getFlow(): Int {
        return sharedPref.getInt(activity.resources.getString(R.string.opned_key), 1)
    }

}