package br.com.joffer.mango.features.payment.creditcard

import br.com.joffer.mango.infra.utils.isValidCreditCard
import java.io.Serializable

data class CreditCard(var number: String = "",
                      var cvv: String = "",
                      var validation: String = "",
                      var owner: String = ""): Serializable{

    fun isCVVValid() = cvv.length == 3
    fun isOwnerValid() = owner.length >= 10
    fun isValidationValid() = validation.length == 5
    fun isNumberValid() = number.length >= 16 && number.isValidCreditCard()



}