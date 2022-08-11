package com.fascinate.flowsdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginStatus = MutableStateFlow<LoginStatus>(LoginStatus.Empty)
    val loginUiState: StateFlow<LoginStatus> = loginStatus

    fun login(username: String, password: String) = viewModelScope.launch {
        loginStatus.value = LoginStatus.Loading
        delay(4000L)

        if(username == "usman" && password == "123456")
            loginStatus.value = LoginStatus.Success
        else
            loginStatus.value = LoginStatus.Error
    }


    sealed class LoginStatus {
        object Success : LoginStatus()
        object Error : LoginStatus()
        object Loading : LoginStatus()
        object Empty : LoginStatus()
    }

//    enum class LoginStatus {
//        SUCCESS,
//        ERROR,
//        LOADING,
//        EMPTY
//    }
}