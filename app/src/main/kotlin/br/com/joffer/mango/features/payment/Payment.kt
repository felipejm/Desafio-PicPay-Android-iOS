package br.com.joffer.mango.features.payment

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Payment(@SerializedName("card_number") val cardNumber: String,
                   @SerializedName("cvv") val cvv: Int,
                   @SerializedName("value") val amount: Double,
                   @SerializedName("expiry_date") val expiryDate: String,
                   @SerializedName("destination_user_id") val userId: String): Serializable