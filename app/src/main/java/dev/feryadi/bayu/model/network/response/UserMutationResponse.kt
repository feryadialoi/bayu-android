package dev.feryadi.bayu.model.network.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.time.LocalDateTime

data class UserMutationResponse(
    @SerializedName(value = "id") val id: Long,
    @SerializedName(value = "originWallet") val originWallet: MutationWalletResponse,
    @SerializedName(value = "destinationWallet") val destinationWallet: MutationWalletResponse,
    @SerializedName(value = "amount") val amount: BigDecimal,
    @SerializedName(value = "description") val description: String,
    @SerializedName(value = "createdDate") val createdDate: String
)
