package br.com.joffer.mango.features.payment.creditcard.model

import br.com.joffer.mango.features.payment.creditcard.CreditCard
import br.com.joffer.mango.infra.utils.PreferenceHelper

private const val CREDIT_CARD = "CREDIT_CARD"

class CardRepository{

    fun save(creditCard: CreditCard){
        PreferenceHelper.write(CREDIT_CARD, creditCard)
    }

    fun get(): CreditCard{
        return PreferenceHelper.read(CREDIT_CARD) as CreditCard
    }
}