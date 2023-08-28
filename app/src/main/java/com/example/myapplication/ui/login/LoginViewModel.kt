package com.example.myapplication.ui.login

import EmployeeDetailsData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.model.LoginRequest
import com.example.myapplication.service.ApiClient
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val apiService = ApiClient.apiService
    val errorMessage = MutableLiveData<String>()
    val listErrorMessage = MutableLiveData<String>()
    val employeeDetailsData = MutableLiveData<EmployeeDetailsData>()
    var loading = MutableLiveData<Boolean>()

    fun callLogin(companyId: String, userId: String, password: String) {
        loading.value = true
        viewModelScope.launch {

            try {
                val requestBody = LoginRequest(
                    companyId,
                    userId,
                    password,
                    "string",
                    "string",
                    0,
                    com.example.myapplication.repository.model.Metadata(
                        "string", "string", "string",
                        emptyMap()
                    )
                )

                val response = apiService.login(requestBody)

                if (response.isSuccessful) {
                    val authToken = response.body()?.data?.KeyId
                    if (authToken != null) {
                        fetchStaffList(authToken)
                    }

                } else {
                    val errorMsg = response.body()?.ErrorMsg ?: "Login failed"
                    errorMessage.value = errorMsg
                    loading.value = false
                }
            } catch (exception: Exception) {
                errorMessage.value = "error occurred"
                loading.value = false
            }
        }
    }

    private suspend fun fetchStaffList(authToken: String) {
        viewModelScope.launch {

            try {

                val response = apiService.getStaffList(authToken)

                if (response.isSuccessful) {
                    employeeDetailsData.value = response.body()?.data
                    loading.value = false
                } else {
                    val errorMsg = response.body()?.ErrorMsg ?: "call staff list failed"
                    listErrorMessage.value = errorMsg
                    loading.value = false

                }
            } catch (exception: Exception) {
                listErrorMessage.value = "error occurred"
                loading.value = false
            }
        }

    }


}