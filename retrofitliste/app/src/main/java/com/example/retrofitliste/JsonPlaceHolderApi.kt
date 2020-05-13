package com.example.retrofitliste

import retrofit2.Call
import retrofit2.http.GET


interface JsonPlaceHolderApi {
    @get:GET("todos")
    val posts: Call<List<TodoItem?>?>?
}