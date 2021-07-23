package com.loud9ja.loud9ja.utils

import android.view.View


interface VoteObserver {
    fun update(view: View?, status: Boolean)
    fun setTotalNumber(totalNumber: Int)
}