package br.com.joffer.mango.features.payment.model

import br.com.joffer.mango.features.payment.Payment
import io.reactivex.Completable
import io.reactivex.Observable

interface PaymentInteractor{

    companion object {
        val instance: PaymentInteractor  by lazy { PaymentInteractorImpl() }
    }

    fun payForContact(payment: Payment): Observable<Transaction>
}