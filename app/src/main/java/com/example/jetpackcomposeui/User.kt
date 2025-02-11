package com.example.jetpackcomposeui

import retrofit2.http.GET
import retrofit2.http.Query

data class User(val id: Int, val name: String, val email: String)
data class Post(val id: Int, val title: String)

interface UserService {
    @GET("users/1")
    suspend fun getUser(): User

    @GET("posts")
    suspend fun getUserPosts(@Query("userId") userId: Int): List<Post>
}