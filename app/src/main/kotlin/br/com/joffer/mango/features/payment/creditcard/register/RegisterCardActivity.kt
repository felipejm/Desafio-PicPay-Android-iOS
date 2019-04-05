package br.com.joffer.mango.features.payment.creditcard.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import br.com.joffer.mango.R
import br.com.joffer.mango.features.payment.creditcard.CreditCard
import br.com.joffer.mango.infra.utils.*
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_register_card.*
import java.util.concurrent.TimeUnit

class RegisterCardActivity: AppCompatActivity(), RegisterCardView{

    companion object {
        fun starter(context: Context){
            context.startActivity(Intent(context, RegisterCardActivity::class.java))
        }
    }

    private val presenter: RegisterCardPresenter = RegisterCardPresenterImpl(this)
    private var diposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_card)
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        diposables.dispose()
    }

    override fun goToPayment() {

    }

    override fun configureClickListeners(){
        back_button.setOnClickListener { onBackPressed() }
        save_card.setOnClickListener {
            presenter.saveCard()
        }
    }

    override fun configureFields(creditCard: CreditCard){
        val cardMask = CreditCardFormatTextWatcher(card_number)
        cardMask.setMaxLength(16)

        card_number.addTextChangedListener(cardMask)
        card_valid.addTextChangedListener(EditTextMask.mask("##/##", card_valid))

        card_number
            .afterTextChangeEvents()
            .skipInitialValue()
            .debounce(300, TimeUnit.MILLISECONDS)
            .onBackgroundThread()
            .subscribe {
                creditCard.number = it.editable.toString()
                presenter.onCardDataChanged()
            }.addTo(diposables)

        card_owner
            .afterTextChangeEvents()
            .skipInitialValue()
            .debounce(300, TimeUnit.MILLISECONDS)
            .onBackgroundThread()
            .subscribe {
                creditCard.owner = it.editable.toString()
                presenter.onCardDataChanged()
            }.addTo(diposables)

        card_cvv
            .afterTextChangeEvents()
            .skipInitialValue()
            .debounce(300, TimeUnit.MILLISECONDS)
            .onBackgroundThread()
            .subscribe {
                creditCard.cvv = it.editable.toString()
                presenter.onCardDataChanged()
            }.addTo(diposables)

        card_valid
            .afterTextChangeEvents()
            .skipInitialValue()
            .debounce(300, TimeUnit.MILLISECONDS)
            .onBackgroundThread()
            .subscribe {
                creditCard.validation = it.editable.toString()
                presenter.onCardDataChanged()
            }.addTo(diposables)
    }

    override fun configureCVVErro(cvvValid: Boolean) {
        if(card_cvv.text?.isNotEmpty() == true) {
            card_cvv_layout.error = getString(R.string.cvv_invalid)
            card_cvv_layout.isErrorEnabled = !cvvValid
        }
    }

    override fun configureNumberErro(numberValid: Boolean) {
        if(card_number.text?.isNotEmpty() == true) {
            card_number_layout.error = getString(R.string.number_invalid)
            card_number_layout.isErrorEnabled = !numberValid
        }
    }

    override fun configureOwnerErro(ownerValid: Boolean) {
        if(card_owner.text?.isNotEmpty() == true) {
            card_owner_layout.error = getString(R.string.owner_invalid)
            card_owner_layout.isErrorEnabled = !ownerValid
        }
    }

    override fun configureValidationErro(validationValid: Boolean) {
        if(card_valid.text?.isNotEmpty() == true) {
            card_valid_layout.error = getString(R.string.validation_invalid)
            card_valid_layout.isErrorEnabled = !validationValid
        }
    }

    override fun showSaveButton() {
        save_card.visible()
    }

    override fun hideSaveButton() {
        save_card.gone()
    }
}