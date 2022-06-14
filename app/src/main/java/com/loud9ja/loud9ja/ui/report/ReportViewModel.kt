package com.loud9ja.loud9ja.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loud9ja.loud9ja.domain.network.api.reports.ReportResponse
import com.loud9ja.loud9ja.domain.repository.ReportRepository
import com.loud9ja.loud9ja.domain.usecase.GetReportUsecase
import com.loud9ja.loud9ja.utils.BaseViewModel
import com.loud9ja.loud9ja.utils.DataState
import com.loud9ja.loud9ja.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportRepository: ReportRepository,
    private val getReportUsecase: GetReportUsecase
) :
    BaseViewModel() {
    private var _createReportDataState: MutableLiveData<DataState<ResponseBody>> = MutableLiveData()
    val createReportDataState: LiveData<DataState<ResponseBody>>
        get() = _createReportDataState

    private var _getReportState: MutableLiveData<DataState<ReportResponse>> = MutableLiveData()
    val getReportState: LiveData<DataState<ReportResponse>>
        get() = _getReportState

    fun createReport(
        state: RequestBody,
        lga: RequestBody,
        category: RequestBody,
        title: RequestBody,
        is_anonymous: RequestBody,
        message: RequestBody,
        media: MultipartBody.Part?
    ) {

//        )
    }

    fun getReports() {
        getReportUsecase().onEach { result ->
            when (result) {
                is NetworkState.Success -> {
                    _getReportState.value = DataState.Success(result.data!!)
                }
                is NetworkState.Loading -> {

                }
                is NetworkState.Error -> {
                    _getReportState.value = DataState.Error(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}