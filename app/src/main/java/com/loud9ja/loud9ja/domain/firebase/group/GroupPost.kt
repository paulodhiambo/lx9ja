package com.loud9ja.loud9ja.domain.firebase.group

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class GroupPost(
    val image: String? = "",
    val userImage: String? = "",
    val date: String? = "",
    val username: String? = "",
    val title: String? = "",
    val body: String? = ""
) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
