package com.loud9ja.loud9ja.ui.polls

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loud9ja.loud9ja.domain.firebase.poll.CreatePollRequest
import com.loud9ja.loud9ja.domain.network.api.polls.PollResponse
import com.loud9ja.loud9ja.domain.network.api.polls.VoteRequest
import com.loud9ja.loud9ja.domain.usecase.CreatePollUseCase
import com.loud9ja.loud9ja.domain.usecase.PollUseCase
import com.loud9ja.loud9ja.domain.usecase.VoteUseCase
import com.loud9ja.loud9ja.utils.NetworkState
import com.loud9ja.loud9ja.utils.UIstate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PollsViewModel @Inject constructor(
    private val pollUseCase: PollUseCase,
    private val voteUseCase: VoteUseCase,
    private val createPollUseCase: CreatePollUseCase
) : ViewModel() {
    private var _pollResponse = MutableLiveData<UIstate<PollResponse>>()
    val pollResponse: LiveData<UIstate<PollResponse>>
        get() = _pollResponse

    private var _voteResponse = MutableLiveData<UIstate<String>>()
    val voteResponse: LiveData<UIstate<String>>
        get() = _voteResponse

    private var _createPollResponse = MutableLiveData<UIstate<String>>()
    val createPollResponse: LiveData<UIstate<String>>
        get() = _createPollResponse

    fun getPolls() {
        pollUseCase().onEach { result ->
            when (result) {
                is NetworkState.Success -> {
                    _pollResponse.value = UIstate.Success(result.data!!)
                }
                is NetworkState.Loading -> {

                }
                is NetworkState.Error -> {
                    _pollResponse.value = UIstate.Error(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun vote(voteRequest: VoteRequest) {
        voteUseCase(voteRequest).onEach { result ->
            when (result) {
                is NetworkState.Success -> {
                    _voteResponse.value = UIstate.Success("success")
                }
                is NetworkState.Loading -> {}
                is NetworkState.Error -> {
                    _voteResponse.value = UIstate.Error(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun createVote(createPollRequest: CreatePollRequest) {
        createPollUseCase(createPollRequest).onEach { result ->
            when (result) {
                is NetworkState.Success -> {
                    _createPollResponse.value = UIstate.Success("success")
                }
                is NetworkState.Loading -> {}
                is NetworkState.Error -> {
                    _createPollResponse.value = UIstate.Error(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}