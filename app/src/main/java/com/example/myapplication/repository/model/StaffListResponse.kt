package com.example.myapplication.repository.model

data class StaffListResponse(
    val data: StaffListData,
    val Error: Boolean,
    val ErrorMsg: String?,
    val ErrorCode: String?,
    val ErrorID: String?
)

data class StaffListData(
    val employees: List<Employee>
)

data class Employee(
    val FullName: String,
    val Designation: String?,
    val UserGuid: String,
    val Image: String
)
