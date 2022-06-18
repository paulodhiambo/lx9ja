package com.loud9ja.loud9ja.ui.group

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loud9ja.loud9ja.domain.network.api.groups.GroupResponse
import com.loud9ja.loud9ja.domain.network.api.groups.response.CreateGroupResponse
import com.loud9ja.loud9ja.domain.usecase.CreateGroupUseCase
import com.loud9ja.loud9ja.domain.usecase.GetgroupUseCase
import com.loud9ja.loud9ja.utils.NetworkState
import com.loud9ja.loud9ja.utils.UIstate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val groupUseCase: GetgroupUseCase,
    private val createGroupUseCase: CreateGroupUseCase
) : ViewModel() {
    private var _groupResponse = MutableLiveData<UIstate<GroupResponse>>()
    val groupResponse: LiveData<UIstate<GroupResponse>>
        get() = _groupResponse

    private var _createGroupResponse = MutableLiveData<UIstate<CreateGroupResponse>>()
    val createGroupResponse: LiveData<UIstate<CreateGroupResponse>>
        get() = _createGroupResponse

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

    fun createGroup(
        name: RequestBody,
        description: RequestBody,
        access: RequestBody,
        invited_people: RequestBody,
        media: File,
        context: Context
    ) {
        createGroupUseCase(
            name,
            description,
            access,
            invited_people,
            media,
            context
        ).onEach { result ->
            when (result) {
                is NetworkState.Success -> {
                    _createGroupResponse.value = UIstate.Success(result.data!!)
                }
                is NetworkState.Loading -> {
                    _createGroupResponse.value = UIstate.Loading()
                }
                is NetworkState.Error -> {
                    _createGroupResponse.value = UIstate.Error(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun Context.saveBitmap(fileName: String, bitmap: Bitmap) = withContext(Dispatchers.IO) {
        val file = File(filesDir, fileName)
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }
}