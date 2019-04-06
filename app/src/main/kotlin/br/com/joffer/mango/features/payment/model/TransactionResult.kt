package br.com.joffer.mango.features.payment.model

import br.com.joffer.mango.features.payment.contacts.Contact
import com.google.gson.annotations.SerializedName

data class TransactionResult(@SerializedName("transaction")val transaction: Transaction)