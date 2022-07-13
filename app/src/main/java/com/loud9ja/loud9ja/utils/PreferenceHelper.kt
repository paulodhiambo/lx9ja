package com.loud9ja.loud9ja.utils

import android.app.Activity
import android.content.Context
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.data.AuthUser

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

    fun saveUser(name: String, email: String, token: String, id: String, image: String) {
        with(sharedPref.edit()) {
            putString("name", name)
            putString("password", email)
            putString("token", token)
            putString("id", id)
            putString("image", image)
            apply()
        }
    }

    fun getUser(): AuthUser? {
        val user = sharedPref.getString("name", null)
        val token = sharedPref.getString("token", null)
        val email = sharedPref.getString("email", null)
        val id = sharedPref.getString("id", null)
        val image = sharedPref.getString("image", null)
        return if (user != null && token != null && email != null) {
            AuthUser(user, email, token, id, image)
        } else {
            null
        }

    }

}