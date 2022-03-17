package com.loud9ja.loud9ja.ui.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.domain.network.api.request.LoginRequest
import com.loud9ja.loud9ja.domain.network.api.response.LoginResponse
import com.loud9ja.loud9ja.domain.network.api.response.RegistrationResponse
import com.loud9ja.loud9ja.domain.usecase.LoginUseCase
import com.loud9ja.loud9ja.domain.usecase.RegisterUseCase
import com.loud9ja.loud9ja.utils.BaseViewModel
import com.loud9ja.loud9ja.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) :
    BaseViewModel() {
    private var _registrationResponse: MutableLiveData<DataState<RegistrationResponse>> =
        MutableLiveData()
    val registrationResponse: LiveData<DataState<RegistrationResponse>>
        get() = _registrationResponse
    private var _loginResponse: MutableLiveData<DataState<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<DataState<LoginResponse>>
        get() = _loginResponse

    fun registerUser(user: User) {
        Log.d("Result=====>", "registerUser: ${user}")
        registerUseCase(user).onEach { result ->
            when (result) {
                is DataState.Success -> {
                    _registrationResponse.value = result
                }
                is DataState.Error -> {
                    _registrationResponse.value = result
                }
                is DataState.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun loginUser(loginRequest: LoginRequest) {
        loginUseCase(loginRequest).onEach { result ->
            when (result) {
                is DataState.Success -> {
                    _loginResponse.value = result
                }
                is DataState.Error -> {
                    _loginResponse.value = result
                }
                is DataState.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun fromJson(json: String?, type: Class<*>?): Any? {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingStrategy(FieldNamingPolicy.IDENTITY)
        val gson = gsonBuilder.serializeNulls().create()
        return gson.fromJson<Any>(json, type)
    }
}