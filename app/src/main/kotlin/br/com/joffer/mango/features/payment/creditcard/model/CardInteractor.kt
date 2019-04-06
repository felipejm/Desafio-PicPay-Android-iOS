package br.com.joffer.mango.features.payment.creditcard.model

import br.com.joffer.mango.features.payment.creditcard.CreditCard

interface CardInteractor{

    companion object {
        val instance: CardInteractor  by lazy { CardInteractorImpl() }
    }

    fun save(creditCard: CreditCard)
    fun get(): CreditCard?
}