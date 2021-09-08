package com.loud9ja.loud9ja.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.loud9ja.loud9ja.repository.ReportRepository
import com.loud9ja.loud9ja.utils.BaseViewModel
import com.loud9ja.loud9ja.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(private val reportRepository: ReportRepository) :
    BaseViewModel() {
    private var _createReportDataState: MutableLiveData<DataState<ResponseBody>> = MutableLiveData()
    val createReportDataState: LiveData<DataState<ResponseBody>>
        get() = _createReportDataState

    fun createReport(
        state: RequestBody,
        lga: RequestBody,
        category: RequestBody,
        title: RequestBody,
        is_anonymous: RequestBody,
        message: RequestBody,
        media: MultipartBody.Part?
    ) {
        compositeDisposable.add(
            reportRepository.createReport(state, lga, category, title, is_anonymous, message, media)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { data -> _createReportDataState.value = DataState.Success(data) },
                    { e -> _createReportDataState.value = DataState.Error(e as Exception) })
        )
    }
}