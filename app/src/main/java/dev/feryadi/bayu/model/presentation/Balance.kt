package dev.feryadi.bayu.model.presentation

import java.math.BigDecimal

data class Balance(
    val id: Long,
    val balance: BigDecimal,
    val initialBalance: BigDecimal
)