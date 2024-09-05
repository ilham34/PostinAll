package com.intern.postinall.data

import com.google.gson.annotations.SerializedName

data class PostResponse(

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("limit")
    val limit: Int,

    @field:SerializedName("skip")
    val skip: Int,

    @field:SerializedName("posts")
    val posts: List<PostsItem>
)

data class PostsItem(

    @field:SerializedName("reactions")
    val reactions: Reactions,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("body")
    val body: String,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("views")
    val views: Int,

    @field:SerializedName("tags")
    val tags: List<String>
)

data class Reactions(

    @field:SerializedName("dislikes")
    val dislikes: Int,

    @field:SerializedName("likes")
    val likes: Int
)
