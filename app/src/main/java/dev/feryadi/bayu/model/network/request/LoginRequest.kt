package dev.feryadi.bayu.model.network.request

import dev.feryadi.bayu.validations.rules.annotations.NotEmpty

data class LoginRequest(
    @NotEmpty
    val username: String,
    @NotEmpty
    val password: String
)