package br.com.joffer.mango.features.payment.model

import br.com.joffer.mango.features.payment.Payment
import br.com.joffer.mango.features.payment.creditcard.CreditCard
import br.com.joffer.mango.features.payment.creditcard.model.CardRepository
import io.reactivex.Completable
import io.reactivex.Observable
import kotlin.concurrent.thread

class PaymentInteractorImpl: PaymentInteractor{

    private val repository = PaymentRepository()

    override fun payForContact(payment: Payment): Observable<Transaction> {
        return repository.payForContact(payment)
    }
}