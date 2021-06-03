package dev.feryadi.bayu.apiservice

import dev.feryadi.bayu.constant.HttpHeader
import dev.feryadi.bayu.constant.MediaType
import dev.feryadi.bayu.model.network.response.ApiResponse
import dev.feryadi.bayu.model.network.response.BalanceResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserBalanceApiService {
    @GET("/api/v1/users/{userId}/balances")
    @Headers(value = [HttpHeader.ACCEPT + ":" + MediaType.APPLICATION_JSON])
    suspend fun getUserBalance(
        @Header(HttpHeader.AUTHORIZATION) token: String,
        @Path("userId") userId: Long
    ): ApiResponse<BalanceResponse>
}