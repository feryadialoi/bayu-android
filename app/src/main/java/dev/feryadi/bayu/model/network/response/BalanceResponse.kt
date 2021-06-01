package dev.feryadi.bayu.model.network.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class BalanceResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("balance") val balance: BigDecimal,
    @SerializedName("initialBalance") val initialBalance: BigDecimal,
)
