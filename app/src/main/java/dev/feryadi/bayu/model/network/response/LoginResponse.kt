package dev.feryadi.bayu.model.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String,
    @SerializedName("userId") val userId: Long,
)