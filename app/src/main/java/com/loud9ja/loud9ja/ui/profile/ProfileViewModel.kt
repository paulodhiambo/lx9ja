package com.loud9ja.loud9ja.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loud9ja.loud9ja.domain.network.api.profile.UserProfileResponse
import com.loud9ja.loud9ja.domain.usecase.GetProfileUserCase
import com.loud9ja.loud9ja.utils.NetworkState
import com.loud9ja.loud9ja.utils.UIstate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUserCase: GetProfileUserCase) :
    ViewModel() {
    private var _profileState = MutableLiveData<UIstate<UserProfileResponse>>()
    val profileState: LiveData<UIstate<UserProfileResponse>>
        get() = _profileState

    fun getUserProfile() {
        profileUserCase().onEach { response ->
            when (response) {
                is NetworkState.Success -> {
                    _profileState.value = UIstate.Success(response.data!!)
                }
                is NetworkState.Loading -> {
                    _profileState.value = UIstate.Loading()
                }
                is NetworkState.Error -> {
                    _profileState.value = UIstate.Error(response.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}