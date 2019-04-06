package br.com.joffer.mango.features.payment.model

import br.com.joffer.mango.features.payment.Payment
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentApi{

    @POST("transaction")
    fun payForContact(@Body payment: Payment): Observable<TransactionResult>
}