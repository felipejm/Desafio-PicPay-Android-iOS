package br.com.joffer.mango.features.payment.model

import br.com.joffer.mango.BuildConfig
import br.com.joffer.mango.features.payment.Payment
import br.com.joffer.mango.infra.RetrofitService
import io.reactivex.Observable

class PaymentRepository{

    private val service by lazy { RetrofitService(PaymentApi::class.java, BuildConfig.BASE_URL).apiService }

    fun payForContact(payment: Payment): Observable<Transaction>{
        return service.payForContact(payment).map { it.transaction }
    }
}