package com.loud9ja.loud9ja.ui.discusion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loud9ja.loud9ja.domain.network.api.comments.PostCommentResponse
import com.loud9ja.loud9ja.domain.network.api.trending.TrendingPostResponse
import com.loud9ja.loud9ja.domain.usecase.PostCommentsUseCase
import com.loud9ja.loud9ja.domain.usecase.TrendingPostUseCase
import com.loud9ja.loud9ja.utils.DataState
import com.loud9ja.loud9ja.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DiscussionViewModel @Inject constructor(
    private val trendingPostUseCase: TrendingPostUseCase,
    private val postCommentsUseCase: PostCommentsUseCase
) :
    ViewModel() {
    private var _postsResponse = MutableLiveData<DataState<TrendingPostResponse>>()

    val postsResponse: LiveData<DataState<TrendingPostResponse>>
        get() = _postsResponse

    fun getTrendingPosts() {
        trendingPostUseCase().onEach { result ->
            when (result) {
                is NetworkState.Success -> {
                    _postsResponse.value = DataState.Success(result.data!!)
                }
                is NetworkState.Loading -> {
                }
                is NetworkState.Error -> {
                    _postsResponse.value = DataState.Error(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    private var _postCommentsResponse = MutableLiveData<DataState<PostCommentResponse>>()
    val postCommentsResponse: LiveData<DataState<PostCommentResponse>>
        get() = _postCommentsResponse

    fun getPostComments(id:Int){
        postCommentsUseCase(id).onEach { result->
            when(result){
                is NetworkState.Success ->{
                    _postCommentsResponse.value = DataState.Success(result.data!!)
                }

                is NetworkState.Error -> {
                    _postCommentsResponse.value = DataState.Error(result.message!!)
                }
                is NetworkState.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}