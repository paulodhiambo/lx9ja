package com.loud9ja.loud9ja.domain.network.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.loud9ja.loud9ja.data.User
import com.loud9ja.loud9ja.domain.network.api.request.LoginRequest
import com.loud9ja.loud9ja.utils.DataState
import com.loud9ja.loud9ja.utils.FireBaseTask

class FireBaseAuthUtil {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _loginState: MutableLiveData<DataState<FireBaseTask>> = MutableLiveData()
    val loginState: LiveData<DataState<FireBaseTask>>
        get() = _loginState

    fun signInUser(loginRequest: LoginRequest) {
        mAuth.signInWithEmailAndPassword(loginRequest.email, loginRequest.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //pass user info
                    _loginState.value = DataState.Success(FireBaseTask.IS_SUCCESSFUL)
                }
                if (task.isCanceled) {
                    //pass error state
                    _loginState.value = DataState.Error(task.exception.toString())
                }
            }
    }

    fun signUpUser(user: User) {
        mAuth.createUserWithEmailAndPassword(user.email!!, user.password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //pass user info
                }
                if (task.isCanceled) {
                    //pass error state
                }
            }
    }
}