package com.loud9ja.loud9ja.stream.ui.home

import com.loud9ja.loud9ja.stream.api.RetrofitBuilder
import com.loud9ja.loud9ja.stream.model.TokenResponse
import okhttp3.Request

class HomeRepository {

  suspend fun fetchAuthToken(request: Request): TokenResponse {
    return RetrofitBuilder.fetchAuthToken(request)
  }
}