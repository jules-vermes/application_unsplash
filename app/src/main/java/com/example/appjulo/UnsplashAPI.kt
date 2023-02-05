package com.example.appjulo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UnsplashAPI {

    @GET("photos")
    fun getPhotos(@Header("Authorization") token: String, @Query("page") page: Int,
                  @Query("per_page") perPage: Int): Call<List<UnsplashPhoto>>
}