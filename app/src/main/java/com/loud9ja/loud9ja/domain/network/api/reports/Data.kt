package com.loud9ja.loud9ja.domain.network.api.reports


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Data(
    @SerializedName("category")
    @Expose
    val category: String, // Emergency
    @SerializedName("created_at")
    @Expose
    val createdAt: String, // 2022-05-12T07:23:55.000000Z
    @SerializedName("deleted_at")
    @Expose
    val deletedAt: Any, // null
    @SerializedName("id")
    @Expose
    val id: Int, // 11
    @SerializedName("is_anonymous")
    @Expose
    val isAnonymous: String, // YES
    @SerializedName("is_free")
    @Expose
    val isFree: String, // NO
    @SerializedName("lga")
    @Expose
    val lga: String, // Demsa
    @SerializedName("media")
    @Expose
    val media: String, // ["reports\/1652340235.jpg","reports\/1652340235.jpg"]
    @SerializedName("message")
    @Expose
    val message: String, // GeheEmenrmSSEmendSSSDDNdnekk
    @SerializedName("state")
    @Expose
    val state: String, // Adamawa
    @SerializedName("status")
    @Expose
    val status: String, // PENDING_VERIFICATION
    @SerializedName("title")
    @Expose
    val title: String, // g g chic
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String, // 2022-05-12T07:23:55.000000Z
    @SerializedName("user_id")
    @Expose
    val userId: Int, // 3
    @SerializedName("view_count")
    @Expose
    val viewCount: Int // 0
)