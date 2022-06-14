package com.loud9ja.loud9ja.domain.network.api.groups


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Data(
    @SerializedName("current_page")
    @Expose
    val currentPage: Int, // 1
    @SerializedName("data")
    @Expose
    val `data`: List<Group>,
    @SerializedName("first_page_url")
    @Expose
    val firstPageUrl: String, // http://loud9ja.travelinnovators.biz/api/v2/groups/list?page=1
    @SerializedName("from")
    @Expose
    val from: Int, // 1
    @SerializedName("last_page")
    @Expose
    val lastPage: Int, // 1
    @SerializedName("last_page_url")
    @Expose
    val lastPageUrl: String, // http://loud9ja.travelinnovators.biz/api/v2/groups/list?page=1
    @SerializedName("links")
    @Expose
    val links: List<Link>,
    @SerializedName("next_page_url")
    @Expose
    val nextPageUrl: Any, // null
    @SerializedName("path")
    @Expose
    val path: String, // http://loud9ja.travelinnovators.biz/api/v2/groups/list
    @SerializedName("per_page")
    @Expose
    val perPage: Int, // 10
    @SerializedName("prev_page_url")
    @Expose
    val prevPageUrl: Any, // null
    @SerializedName("to")
    @Expose
    val to: Int, // 9
    @SerializedName("total")
    @Expose
    val total: Int // 9
)