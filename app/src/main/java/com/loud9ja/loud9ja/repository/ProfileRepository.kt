package com.loud9ja.loud9ja.repository

import com.loud9ja.loud9ja.network.LoudAPI
import com.loud9ja.loud9ja.network.api.response.ProfileResponse
import io.reactivex.Observable

class ProfileRepository constructor(private val api: LoudAPI) {
    fun getUserProfile(): Observable<ProfileResponse> =
        api.getUserProfile()
}