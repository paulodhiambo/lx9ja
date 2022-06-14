package com.loud9ja.loud9ja.ui.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loud9ja.loud9ja.domain.network.api.groups.GroupResponse
import com.loud9ja.loud9ja.domain.usecase.GetgroupUseCase
import com.loud9ja.loud9ja.utils.NetworkState
import com.loud9ja.loud9ja.utils.UIstate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(private val groupUseCase: GetgroupUseCase) : ViewModel() {
    private var _groupResponse = MutableLiveData<UIstate<GroupResponse>>()
    val groupResponse: LiveData<UIstate<GroupResponse>>
        get() = _groupResponse

    fun getGroup() {
        groupUseCase().onEach { result ->
            when (result) {
                is NetworkState.Success -> {
                    _groupResponse.value = UIstate.Success(result.data!!)
                }
                is NetworkState.Loading -> {
                    _groupResponse.value = UIstate.Loading()
                }
                is NetworkState.Error -> {
                    _groupResponse.value = UIstate.Error(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}