package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.network.api.groups.GroupResponse
import com.loud9ja.loud9ja.domain.network.api.groups.response.CreateGroupResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class GroupRepository @Inject constructor(private val api: LoudAPI) {
    suspend fun getGroups(): GroupResponse = api.getGroups()
    suspend fun createGroup(
        name: RequestBody,
        description: RequestBody,
        access: RequestBody,
        invited_people: RequestBody,
        media: MultipartBody.Part?
    ): CreateGroupResponse = api.createGroup(name, description, access, invited_people, media)
}