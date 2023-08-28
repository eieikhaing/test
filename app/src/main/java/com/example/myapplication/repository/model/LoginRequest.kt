package com.example.myapplication.repository.model
data class LoginRequest(
    val CompanyId: String,
    val UserId: String,
    val Password: String,
    val DeviceId:String?,
    val VersionName:String?,
    val VersionCode:Int?,
    val Metadata: Metadata
)
data class Metadata(
    val Manufacturer: String,
    val ModelNumber: String,
    val BuildNumber: String,
    val Additional: Map<String, Any>
)