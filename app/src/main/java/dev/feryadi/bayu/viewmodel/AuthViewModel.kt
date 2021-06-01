package dev.feryadi.bayu.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.feryadi.bayu.model.network.request.LoginRequest
import dev.feryadi.bayu.model.network.response.LoginResponse
import dev.feryadi.bayu.repository.AuthRepository
import dev.feryadi.bayu.resourcestate.ResourceState
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun login(loginRequest: LoginRequest): LiveData<ResourceState<LoginResponse, Exception>> {
        return liveData<ResourceState<LoginResponse, Exception>>(Dispatchers.IO) {
            emit(ResourceState.loading())
            try {
                val response = authRepository.login(loginRequest)
                emit(ResourceState.success(response.data))
            } catch (exception: Exception) {
                emit(ResourceState.error(exception))
            }
        }
    }

    fun mockLogin(): LiveData<String> {
        return liveData<String>(Dispatchers.IO) {
            emit("Hello World")
        }
    }


}