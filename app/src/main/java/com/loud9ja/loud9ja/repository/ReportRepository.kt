package com.loud9ja.loud9ja.repository

import com.loud9ja.loud9ja.network.LoudAPI
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody

class ReportRepository constructor(private val api: LoudAPI) {
    fun createReport(
        state: RequestBody,
        lga: RequestBody,
        category: RequestBody,
        title: RequestBody,
        is_anonymous: RequestBody,
        message: RequestBody,
        media: MultipartBody.Part?
    ): Observable<ResponseBody> {
        return api.createReport(state, lga, category, title, is_anonymous, message, media)
    }
}