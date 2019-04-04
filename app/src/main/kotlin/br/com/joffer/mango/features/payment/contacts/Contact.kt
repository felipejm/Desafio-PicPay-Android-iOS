package br.com.joffer.mango.features.payment.contacts

import com.google.gson.annotations.SerializedName

data class Contact(@SerializedName("id") val id: String,
                   @SerializedName("name") val name: String,
                   @SerializedName("username") val username: String,
                   @SerializedName("img") val img: String)