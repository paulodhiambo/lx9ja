package com.loud9ja.loud9ja.ui.discusion

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loud9ja.loud9ja.domain.network.api.comments.AddCommentRequest
import com.loud9ja.loud9ja.domain.network.api.comments.AddCommentResponse
import com.loud9ja.loud9ja.domain.network.api.comments.PostCommentsResponse
import com.loud9ja.loud9ja.domain.network.api.posts.LikePostRequest
import com.loud9ja.loud9ja.domain.network.api.posts.LikePostResponse
import com.loud9ja.loud9ja.domain.network.api.trending.TrendingPostResponse
import com.loud9ja.loud9ja.domain.usecase.AddPostCommentUseCase
import com.loud9ja.loud9ja.domain.usecase.LikePostUseCase
import com.loud9ja.loud9ja.domain.usecase.CreatePostUseCase
import com.loud9ja.loud9ja.domain.usecase.PostCommentsUseCase
import com.loud9ja.loud9ja.domain.usecase.TrendingPostUseCase
import com.loud9ja.loud9ja.utils.DataState
import com.loud9ja.loud9ja.utils.NetworkState
import com.loud9ja.loud9ja.utils.UIstate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DiscussionViewModel @Inject constructor(
    private val trendingPostUseCase: TrendingPostUseCase,
    private val postCommentsUseCase: PostCommentsUseCase,
    private val addPostCommentUseCase: AddPostCommentUseCase,
    private val likePostUseCase: LikePostUseCase
    private val createPostUseCase: CreatePostUseCase
) :
    ViewModel() {
    private var _postsResponse = MutableLiveData<DataState<TrendingPostResponse>>()

    val postsResponse: LiveData<DataState<TrendingPostResponse>>
        get() = _postsResponse

    private var _createPostsResponse = MutableLiveData<UIstate<ResponseBody>>()

    val createPostsResponse: LiveData<UIstate<ResponseBody>>
        get() = _createPostsResponse

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

    private var _postCommentsResponse = MutableLiveData<DataState<PostCommentsResponse>>()
    val postCommentsResponse: LiveData<DataState<PostCommentsResponse>>
        get() = _postCommentsResponse

    fun getPostComments(id: Int) {
        postCommentsUseCase(id).onEach { result ->
            when (result) {
                is NetworkState.Success -> {
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

    private var _addCommentsResponse = MutableLiveData<DataState<AddCommentResponse>>()
    val addCommentsResponse: LiveData<DataState<AddCommentResponse>>
        get() = _addCommentsResponse

    fun addPostComment(addCommentRequest: AddCommentRequest) {
        addPostCommentUseCase(addCommentRequest).onEach { result ->
            when (result) {
                is NetworkState.Success -> {
                    _addCommentsResponse.value = DataState.Success(result.data!!)
                }

                is NetworkState.Error -> {
                    _addCommentsResponse.value = DataState.Error(result.message!!)
                }
                is NetworkState.Loading -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    private var _likePostResponse = MutableLiveData<DataState<LikePostResponse>>()
    val likePostResponse: LiveData<DataState<LikePostResponse>>
        get() = _likePostResponse

    fun likePost(likePostRequest: LikePostRequest){
        likePostUseCase(likePostRequest).onEach { result ->
            when(result){
                is NetworkState.Success ->{
                    _likePostResponse.value = DataState.Success(result.data!!)
                }

                is NetworkState.Error -> {
                    _likePostResponse.value = DataState.Error(result.message!!)
                }
                is NetworkState.Loading -> {}
            }

    fun createPost(
        groupName: RequestBody,
        groupDescription: RequestBody,
        file: File,
        requireContext: Context
    ) {
        createPostUseCase(
            groupName,
            groupDescription,
            file,
            requireContext
        ).onEach { result ->
            when (result) {
                is NetworkState.Success -> {
                    _createPostsResponse.value = UIstate.Success(result.data!!)
                }
                is NetworkState.Loading -> {
                    _createPostsResponse.value = UIstate.Loading()
                }
                is NetworkState.Error -> {
                    _createPostsResponse.value = UIstate.Error(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}