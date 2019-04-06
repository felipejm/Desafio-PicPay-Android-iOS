package br.com.joffer.mango.features.payment.model

import br.com.joffer.mango.features.payment.contacts.Contact
import com.google.gson.annotations.SerializedName

data class Transaction(@SerializedName("id")val id: String,
                       @SerializedName("value")val amount: String,
                       @SerializedName("timestamp")val timestamp: Long,
                       @SerializedName("destination_user")val contact: Contact)