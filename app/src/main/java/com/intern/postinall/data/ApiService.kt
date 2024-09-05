package com.intern.postinall.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getPost(): Response<PostResponse>

    @GET("posts/{id}")
    suspend fun getDetailPost(
        @Path("id") id: Int
    ): Response<PostsItem>
}