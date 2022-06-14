package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.network.api.groups.GroupResponse
import javax.inject.Inject

class GroupRepository @Inject constructor(private val api: LoudAPI) {
    suspend fun getGroups(): GroupResponse = api.getGroups()
}