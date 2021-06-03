package dev.feryadi.bayu.apiservice

import dev.feryadi.bayu.constant.HttpHeader
import dev.feryadi.bayu.constant.MediaType
import dev.feryadi.bayu.model.network.response.ApiResponse
import dev.feryadi.bayu.model.network.response.UserMutationResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserMutationApiService {
    @GET(value = "/api/v1/users/{userId}/mutations")
    @Headers(value = [HttpHeader.ACCEPT + ":" + MediaType.APPLICATION_JSON])
    suspend fun getUserMutations(
        @Header(HttpHeader.AUTHORIZATION) token: String,
        @Path(value = "userId") userId: Long
    ): ApiResponse<List<UserMutationResponse>>
}