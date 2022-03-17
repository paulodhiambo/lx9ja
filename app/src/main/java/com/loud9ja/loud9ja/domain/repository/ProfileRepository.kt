package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.network.api.response.ProfileResponse
import io.reactivex.Observable
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: LoudAPI) {
    suspend fun getUserProfile(): ProfileResponse =
        api.getUserProfile()
}