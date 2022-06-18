package com.loud9ja.loud9ja.domain.usecase

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.loud9ja.loud9ja.domain.network.api.groups.response.CreateGroupResponse
import com.loud9ja.loud9ja.domain.repository.GroupRepository
import com.loud9ja.loud9ja.utils.NetworkState
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class CreateGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    operator fun invoke(
        name: RequestBody,
        description: RequestBody,
        access: RequestBody,
        invited_people: RequestBody,
        media: File,
        context: Context
    ): Flow<NetworkState<CreateGroupResponse>> = flow {
        try {
            emit(NetworkState.Loading())
            // val compressedImageFile = Compressor.compress(context, media)
            val compressedImageFile = Compressor.compress(context, media) {
                resolution(720, 400)
                quality(80)
                format(Bitmap.CompressFormat.PNG)
                size(1_007_002) // 2 MB
            }
            val requestFile =
                compressedImageFile.asRequestBody("multipart/form-data".toMediaTypeOrNull());
            val image =
                requestFile.let {
                    MultipartBody.Part.createFormData(
                        "media",
                        compressedImageFile.name,
                        it
                    )
                }
            val result = repository.createGroup(name, description, access, invited_people, image)
            Log.d("Result===========>", "invoke: ${result.errors}")
            emit(NetworkState.Success(result))

        } catch (e: IOException) {
            emit(NetworkState.Error(e.message.toString()))
        } catch (e: HttpException) {
            emit(NetworkState.Error("Group with similar name exists"))
        }
    }
}