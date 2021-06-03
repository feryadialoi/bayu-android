package dev.feryadi.bayu.model.network.response

import com.google.gson.annotations.SerializedName

data class MutationWalletResponse(
    @SerializedName(value = "id") val id: Long,
    @SerializedName(value = "address") val address: String
)
