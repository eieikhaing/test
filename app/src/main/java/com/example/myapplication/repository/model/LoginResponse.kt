package com.example.myapplication.repository.model

data class LoginResponse(
    val data: LoginResponseData,
    val Error: Boolean,
    val ErrorMsg: String?,
    val ErrorCode: String?,
    val ErrorID: String?
)

data class LoginResponseData(
    val CompanyGUID: String,
    val UserGUID: String,
    val isSuperUser: Boolean,
    val KeyId: String,
    val success: Boolean
)

