package dev.feryadi.bayu.model.network.response

data class ApiResponse<D>(
    val status: Int,
    val message: String,
    val data: D,
)
