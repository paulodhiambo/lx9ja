package com.loud9ja.loud9ja.domain.repository

import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.network.api.polls.PollResponse
import com.loud9ja.loud9ja.domain.network.api.polls.VoteRequest
import okhttp3.ResponseBody
import javax.inject.Inject

class PollRepository @Inject constructor(private val api: LoudAPI) {

    suspend fun getPolls(): PollResponse = api.getPolls()

    suspend fun vote(voteRequest: VoteRequest): ResponseBody = api.vote(voteRequest)
}