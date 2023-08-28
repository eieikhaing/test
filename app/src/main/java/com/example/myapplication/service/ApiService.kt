package com.example.myapplication.service

import EmployeeDetailsResponse
import com.example.myapplication.repository.model.LoginRequest
import com.example.myapplication.repository.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("Individual/login")
    suspend fun login(
        @Body requestBody: LoginRequest
    ): Response<LoginResponse>

      @GET("Staff")
      suspend fun getStaffList(
          @Header("AccessToken") authToken: String
      ): Response<EmployeeDetailsResponse>
}
