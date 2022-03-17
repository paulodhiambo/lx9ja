package com.loud9ja.loud9ja.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.loud9ja.loud9ja.domain.network.api.response.ProfileResponse
import com.loud9ja.loud9ja.domain.repository.ProfileRepository
import com.loud9ja.loud9ja.utils.BaseViewModel
import com.loud9ja.loud9ja.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val profileRepository: ProfileRepository) :
    BaseViewModel() {
    private val _profileDataState: MutableLiveData<DataState<ProfileResponse>> = MutableLiveData()
    val profileDataState: LiveData<DataState<ProfileResponse>>
        get() = _profileDataState

    fun getUserProfile() {
//        compositeDisposable.add(
//            profileRepository.getUserProfile()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    { data -> _profileDataState.value = DataState.Success(data) },
//                    { e -> DataState.Error(e as Exception) })
//        )
    }
}