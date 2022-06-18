package com.loud9ja.loud9ja.domain.usecase

import android.content.Context
import android.graphics.Bitmap
import com.loud9ja.loud9ja.domain.repository.PostsRepository
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
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(private val postsRepository: PostsRepository) {
    operator fun invoke(
        title: RequestBody,
        description: RequestBody,
        media: File,
        context: Context
    ): Flow<NetworkState<ResponseBody>> = flow {
        try {
            emit(NetworkState.Loading())
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
            val result = postsRepository.createPost(title, description, image)
            emit(NetworkState.Success(result))
        } catch (e: IOException) {
            emit(NetworkState.Error("An error occurred"))
        } catch (e: HttpException) {
            emit(NetworkState.Error("An error occurred"))
        }
    }
}