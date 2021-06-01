package dev.feryadi.bayu.repository

import dev.feryadi.bayu.apiservice.UserBalanceApiService
import dev.feryadi.bayu.model.network.response.ApiResponse
import dev.feryadi.bayu.model.network.response.BalanceResponse
import javax.inject.Inject

class UserBalanceRepository @Inject constructor(
    private val userBalanceApiService: UserBalanceApiService
) {
    suspend fun getUserBalance(token: String, userId: Long): ApiResponse<BalanceResponse> {
        return userBalanceApiService.getUserBalance(token, userId)
    }
}