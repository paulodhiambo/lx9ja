package com.loud9ja.loud9ja.ui.authentication

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.loud9ja.loud9ja.data.Platform
import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.network.LoudAPI
import com.loud9ja.loud9ja.network.api.request.LoginRequest
import com.loud9ja.loud9ja.network.api.response.LoginResponse
import com.loud9ja.loud9ja.network.api.response.RegistrationResponse
import com.loud9ja.loud9ja.repository.AuthRepository
import com.loud9ja.loud9ja.utils.AuthPreference
import com.loud9ja.loud9ja.utils.BaseViewModel
import com.loud9ja.loud9ja.utils.Constants.BASE_URL
import com.loud9ja.loud9ja.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authPreference: AuthPreference
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
        compositeDisposable.add(
            authRepository.registerUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    _registrationResponse.value = DataState.Success(data)
                }, {
                    _registrationResponse.value = DataState.Error(it as Exception)
                })
        )
    }

    fun register(user: User, context: Context) {
        val requestQueue = Volley.newRequestQueue(context)

        val postData = JSONObject()
        try {
            postData.put("email", user.email)
            postData.put("password", user.password)
            postData.put("name", user.name)
            postData.put("age", user.age)
            postData.put("gender", user.gender)
            postData.put("password_confirmation", user.password_confirmation)
            postData.put("platform", Platform.LOUD.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            "${BASE_URL}${LoudAPI.REGISTER_API}",
            postData,
            { response ->
                val result: RegistrationResponse =
                    fromJson(
                        response.toString(),
                        RegistrationResponse::class.java
                    ) as RegistrationResponse
                _registrationResponse.value = DataState.Success(result)
                Log.d("TAG", "register: $result")
                println(response)
            }
        ) { error ->
            _registrationResponse.value = DataState.Error(error as Exception)
            error.printStackTrace()
        }

        requestQueue.add(jsonObjectRequest)
    }

    fun loginUser(loginRequest: LoginRequest) {
        compositeDisposable.add(
            authRepository.loginUser(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    Log.d("AUth", "loginUser: $data")
                    authPreference.saveData(
                        loginRequest.email,
                        loginRequest.password,
                        data.access_token
                    )
                    _loginResponse.value = DataState.Success(data)
                }, {
                    _loginResponse.value = DataState.Error(it as Exception)
                })
        )
    }

    fun login(loginRequest: LoginRequest, context: Context) {
        val requestQueue = Volley.newRequestQueue(context)

        val postData = JSONObject()
        try {
            postData.put("email", loginRequest.email)
            postData.put("password", loginRequest.password)
            postData.put("platform", Platform.LOUD.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            "${BASE_URL}${LoudAPI.LOGIN_API}",
            postData,
            { response ->
                val result: LoginResponse =
                    fromJson(response.toString(), LoginResponse::class.java) as LoginResponse
                authPreference.saveData(
                    loginRequest.email,
                    loginRequest.password,
                    result.access_token
                )
                _loginResponse.value = DataState.Success(result)
                Log.d("TAG", "login: $result")
                println(response)
            }
        ) { error ->
            _loginResponse.value = DataState.Error(error as Exception)
            error.printStackTrace()
        }

        requestQueue.add(jsonObjectRequest)
    }

    private fun fromJson(json: String?, type: Class<*>?): Any? {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingStrategy(FieldNamingPolicy.IDENTITY)
        val gson = gsonBuilder.serializeNulls().create()
        return gson.fromJson<Any>(json, type)
    }
}