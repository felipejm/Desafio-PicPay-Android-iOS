package br.com.joffer.mango.features.payment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.joffer.mango.R
import br.com.joffer.mango.features.payment.contacts.Contact
import br.com.joffer.mango.features.payment.creditcard.CreditCard
import br.com.joffer.mango.features.payment.model.Transaction
import br.com.joffer.mango.features.payment.receipt.ReceiptBottomSheet
import br.com.joffer.mango.infra.utils.KeyboardHelper
import br.com.joffer.mango.infra.utils.gone
import br.com.joffer.mango.infra.utils.onBackgroundThread
import br.com.joffer.mango.infra.utils.visible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding3.widget.TextViewAfterTextChangeEvent
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity: AppCompatActivity(), PaymentView{

    companion object {
        private const val CONTACT = "CONTACT"
        private const val CREDIT_CARD = "CREDIT_CARD"

        fun starter(context: Context, contact:Contact, creditCard: CreditCard){
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra(CONTACT, contact)
            intent.putExtra(CREDIT_CARD, creditCard)
            context.startActivity(intent)
        }
    }

    private val presenter: PaymentPresenter = PaymentPresenterImpl(this)
    private var diposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        presenter.onReceiveSerializable(intent?.getSerializableExtra(CONTACT))
        presenter.onReceiveSerializable(intent?.getSerializableExtra(CREDIT_CARD))
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        diposable?.dispose()
    }

    override fun configureClickListeners(){
        back_button.setOnClickListener { onBackPressed() }
        pay.setOnClickListener {
            KeyboardHelper.hideSoftKeyboard(this, amount)
            presenter.pay(amount.text.toString())
        }
    }

    override fun configureContact(contact: Contact) {
        userName.text = contact.username

        Glide.with(this)
            .load(contact.img)
            .apply(RequestOptions().circleCrop())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(avatar)
    }

    override fun configureCreditCard(creditCard: CreditCard) {
        val cardNumber = creditCard.number.takeLast(4)
        credit_card_name.text = "Mastercard $cardNumber •"
    }

    override fun configureAmount(){
        val hintColor = ContextCompat.getColor(this, R.color.payment_amount_hint)
        val greenColor = ContextCompat.getColor(this, R.color.green)
        amount.setCurrency("")

        diposable = amount.afterTextChangeEvents()
            .skipInitialValue()
            .onBackgroundThread()
            .subscribe {
                configureCurrencySymbol(it, hintColor, greenColor)
                pay.isEnabled = it.editable.toString().isNotBlank()
            }
    }

    private fun configureCurrencySymbol(
        it: TextViewAfterTextChangeEvent,
        hintColor: Int,
        greenColor: Int) {
        if (it.editable.toString().isEmpty()) {
            label_amount.setTextColor(hintColor)
        } else {
            label_amount.setTextColor(greenColor)
        }
    }

    override fun showSuccess(transaction: Transaction, creditCard: CreditCard) {
        ReceiptBottomSheet.showReceipt(this, transaction, creditCard)
    }

    override fun showLoading() {
        form_group.gone()
        progress_bar.visible()
    }

    override fun hideLoading() {
        form_group.visible()
        progress_bar.gone()
    }
}