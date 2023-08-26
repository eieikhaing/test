package com.example.myapplication.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Set logging level
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Add logging interceptor
        .build()

     val retrofit = Retrofit.Builder()
        .baseUrl("https://testapiv4.justlogin.com/api/") // Base URL of the API
        .addConverterFactory(GsonConverterFactory.create())
        .client(client) // Use the OkHttpClient with the interceptor
        .build()

    val apiService = retrofit.create(ApiService::class.java)

}