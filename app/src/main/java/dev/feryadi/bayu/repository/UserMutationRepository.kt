package dev.feryadi.bayu.repository

import dev.feryadi.bayu.apiservice.UserMutationApiService
import dev.feryadi.bayu.model.network.response.ApiResponse
import dev.feryadi.bayu.model.network.response.UserMutationResponse
import javax.inject.Inject

class UserMutationRepository @Inject constructor(
    private val userMutationApiService: UserMutationApiService
) {
    suspend fun getUserMutations(token: String, userId: Long): ApiResponse<List<UserMutationResponse>> {
        return userMutationApiService.getUserMutations(token, userId)
    }
}