package com.loud9ja.loud9ja.domain.network.api.groups


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Link(
    @SerializedName("active")
    @Expose
    val active: Boolean, // false
    @SerializedName("label")
    @Expose
    val label: String, // &laquo; Previous
    @SerializedName("url")
    @Expose
    val url: String // http://loud9ja.travelinnovators.biz/api/v2/groups/list?page=1
)