package dev.feryadi.bayu.apiservice

import dev.feryadi.bayu.model.network.request.LoginRequest
import dev.feryadi.bayu.model.network.response.ApiResponse
import dev.feryadi.bayu.model.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {
    @POST("/login")
    @Headers("Accept: application/json")
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<LoginResponse>
}