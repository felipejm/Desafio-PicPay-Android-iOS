package br.com.joffer.mango.features.payment.creditcard.model

import br.com.joffer.mango.features.payment.creditcard.CreditCard
import kotlin.concurrent.thread

class CardInteractorImpl: CardInteractor{

    private val repository = CardRepository()

    override fun save(creditCard: CreditCard){
        thread(start = true) { repository.save(creditCard) }
    }

    override fun get(): CreditCard?{
        return repository.get()
    }
}