package dev.feryadi.bayu.repository

import dev.feryadi.bayu.apiservice.AuthApiService
import dev.feryadi.bayu.model.network.request.LoginRequest
import dev.feryadi.bayu.model.network.response.ApiResponse
import dev.feryadi.bayu.model.network.response.LoginResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService
) {
    suspend fun login(loginRequest: LoginRequest): ApiResponse<LoginResponse> {
        return authApiService.login(loginRequest)
    }
}