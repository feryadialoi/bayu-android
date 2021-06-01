package dev.feryadi.bayu.apiservice

import dev.feryadi.bayu.model.network.response.ApiResponse
import dev.feryadi.bayu.model.network.response.BalanceResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserBalanceApiService {
    @GET("/api/v1/users/{userId}/balances")
    suspend fun getUserBalance(
        @Header("Authorization") token: String,
        @Path("userId") userId: Long): ApiResponse<BalanceResponse>
}