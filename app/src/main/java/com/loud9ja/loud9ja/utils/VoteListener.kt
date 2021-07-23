package com.loud9ja.loud9ja.utils

import android.view.View


interface VoteListener {
    fun onItemClick(view: View?, index: Int, status: Boolean): Boolean
}