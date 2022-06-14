package com.loud9ja.loud9ja.domain.usecase

import com.loud9ja.loud9ja.domain.network.api.reports.ReportResponse
import com.loud9ja.loud9ja.domain.repository.ReportRepository
import com.loud9ja.loud9ja.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetReportUsecase @Inject constructor(private val reportRepository: ReportRepository) {
    operator fun invoke(): Flow<NetworkState<ReportResponse>> = flow {
        try {
            emit(NetworkState.Loading())
            val result = reportRepository.getReports()
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error(e.message.toString()))
        } catch (e: HttpException) {
            emit(NetworkState.Error(e.message()))
        }
    }
}