package com.example.myapplication.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.model.Employee
import com.example.myapplication.repository.model.LoginRequest
import com.example.myapplication.service.ApiClient
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val apiService = ApiClient.apiService
    val errorMessage = MutableLiveData<String>()
    val listErrorMessage = MutableLiveData<String>()
    val staffListLiveData = MutableLiveData<List<Employee>>()
    private val _staffList = MutableLiveData<List<Employee>>()
    val staffList: LiveData<List<Employee>> = _staffList

    init {
        // Initialize the staff list with sample data
        val sampleStaffList = listOf(
            Employee("John Doe", "Engineer","","https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg"),
            Employee("Jane Smith", "Designer","","https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg"),
            Employee("Michael Johnson", "Manager","","https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg") ,
            Employee("Ei", "Manager","","https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg")

        )
        _staffList.value = sampleStaffList
    }

    fun callLogin(companyId: String, userId: String, password: String) {
        viewModelScope.launch {

            try {
                val requestBody = LoginRequest(companyId, userId, password)
                val response = apiService.login(requestBody)

                if (response.isSuccessful) {
                    val authToken = "Bearer ${response.body()?.data?.KeyId}"
                    fetchStaffList(authToken)

                } else {
                    val errorMsg = response.body()?.ErrorMsg ?: "Login failed"

                    errorMessage.value = errorMsg
                    val authToken = "21479CDDC7644A69BDBE66B587F051D5"
                    fetchStaffList(authToken)

                }
            } catch (exception: Exception) {
                errorMessage.value = "error occurred"

            }
        }
    }

    private suspend fun fetchStaffList(authToken: String) {
        viewModelScope.launch {

            try {
                //  val requestBody = LoginRequest(companyId, userId, password)
                val response = apiService.getStaffList(authToken)

                if (response.isSuccessful) {
                    Log.d("##", "success")
                    staffListLiveData.value = response.body()?.data?.employees

                } else {
                    val errorMsg = response.body()?.ErrorMsg ?: "Login failed"
                    Log.d("##", "failed")
                    listErrorMessage.value = errorMsg


                }
            } catch (exception: Exception) {
                listErrorMessage.value = "error occurred"

            }
        }

    }

    /* private val _text = MutableLiveData<String>().apply {
         value = "This is dashboard Fragment"
     }
     val text: LiveData<String> = _text*/

}