package com.loud9ja.loud9ja

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import dagger.hilt.android.HiltAndroidApp
import live.videosdk.rtc.android.Meeting
import live.videosdk.rtc.android.VideoSDK

@HiltAndroidApp
class Loud9ja : Application() {
    private lateinit var meeting: Meeting

    fun getMeeting(): Meeting {
        return meeting
    }

    fun setMeeting(meeting: Meeting) {
        this.meeting = meeting
    }

    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
        VideoSDK.initialize(applicationContext)
        initFacebook()
    }

    private fun initFacebook() {
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}