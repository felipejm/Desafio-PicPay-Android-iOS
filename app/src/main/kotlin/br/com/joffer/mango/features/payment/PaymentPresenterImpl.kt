package br.com.joffer.mango.features.payment

import android.util.Log
import br.com.joffer.mango.features.payment.contacts.Contact
import br.com.joffer.mango.features.payment.creditcard.CreditCard
import br.com.joffer.mango.features.payment.model.PaymentInteractor
import br.com.joffer.mango.infra.utils.onBackgroundThread
import io.reactivex.disposables.Disposable
import java.io.Serializable

class PaymentPresenterImpl(val view: PaymentView) : PaymentPresenter {

    private var contact: Contact? = null
    private var creditCard: CreditCard? = null
    private val interactor = PaymentInteractor.instance
    private var disposable: Disposable? = null

    override fun onCreate() {
        view.configureAmount()
        view.configureClickListeners()

        contact?.let { view.configureContact(it) }
        creditCard?.let { view.configureCreditCard(it) }
    }

    override fun onDestroy() {
        disposable?.dispose()
    }

    override fun onReceiveSerializable(serializable: Serializable?) {
        when (serializable) {
            is CreditCard -> creditCard = serializable
            is Contact -> contact = serializable
        }
    }

    override fun pay(amount: String) {
        if (contact != null && creditCard != null) {
            val amount = amount.replace(".", "").replace(",", ".").trim().toDouble()

            val payment = Payment(
                cardNumber = creditCard!!.number,
                userId = contact!!.id,
                expiryDate = creditCard!!.validation,
                amount = amount,
                cvv = creditCard!!.cvv.toInt()
            )

            disposable = interactor.payForContact(payment)
                .onBackgroundThread()
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .subscribe({
                    view.showSuccess(it, creditCard!!)
                }, {
                    Log.e("PaymentPresenter", it.message, it)
                })
        }
    }
}