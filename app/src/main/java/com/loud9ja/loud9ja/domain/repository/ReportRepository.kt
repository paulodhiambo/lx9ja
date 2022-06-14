package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.network.api.reports.ReportResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

class ReportRepository @Inject constructor(private val api: LoudAPI) {
    suspend fun createReport(
        state: RequestBody,
        lga: RequestBody,
        category: RequestBody,
        title: RequestBody,
        is_anonymous: RequestBody,
        message: RequestBody,
        media: MultipartBody.Part?
    ): ResponseBody {
        return api.createReport(state, lga, category, title, is_anonymous, message, media)
    }

    suspend fun getReports(): ReportResponse = api.getReports()
}