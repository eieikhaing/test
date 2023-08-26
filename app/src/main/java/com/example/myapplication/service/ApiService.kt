package com.example.myapplication.service

import com.example.myapplication.repository.model.LoginRequest
import com.example.myapplication.repository.model.LoginResponse
import com.example.myapplication.repository.model.StaffListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("Authentication/login")
    suspend fun login(
        @Body requestBody: LoginRequest
    ): Response<LoginResponse>

      @GET("Staff/kiosk")
      suspend fun getStaffList(
          @Header("AccessToken") authToken: String
      ): Response<StaffListResponse>
}
