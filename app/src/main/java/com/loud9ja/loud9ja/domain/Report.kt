package com.loud9ja.loud9ja.domain

import java.util.*

data class Report(
    val title: String,
    val body: String,
    val dateTimePosted: Date,
    val isNew: Boolean
)