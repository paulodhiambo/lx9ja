package com.loud9ja.loud9ja.utils

import android.content.Context
import android.content.SharedPreferences
import com.loud9ja.loud9ja.data.AuthUser
import javax.inject.Inject

class AuthPreference @Inject constructor(val context: Context) {
    fun saveData(name: String, password: String, token: String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences("auth", Context.MODE_PRIVATE).edit()
        editor.putString("name", name)
        editor.putString("password", password)
        editor.putString("token", token)
        editor.apply()
    }

    fun getAuthDetails(): AuthUser? {
        val prefs: SharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        val name = prefs.getString("name", null)
        val password = prefs.getString("password", null)
        val token = prefs.getString("token", null)
        return if (name != null && password != null && token != null) {
            AuthUser(name, password, token)
        } else {
            null
        }
    }

    fun removeData() {
        val prefs: SharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit().remove("name").apply()
        prefs.edit().remove("password").apply()
        prefs.edit().remove("token").apply()
    }

    fun savePin(pin: String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences("auth", Context.MODE_PRIVATE).edit()
        editor.putString("pin", pin)
        editor.apply()
    }

    fun getPin(): String? {
        val prefs: SharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        return prefs.getString("pin", null)
    }
}