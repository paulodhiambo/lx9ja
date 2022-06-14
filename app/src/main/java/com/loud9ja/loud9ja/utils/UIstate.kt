package com.loud9ja.loud9ja.utils

sealed class UIstate<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : UIstate<T>(data)
    class Error<T>(message: String, data: T? = null) : UIstate<T>(data, message)
    class Loading<T>(data: T? = null) : UIstate<T>(data)
}