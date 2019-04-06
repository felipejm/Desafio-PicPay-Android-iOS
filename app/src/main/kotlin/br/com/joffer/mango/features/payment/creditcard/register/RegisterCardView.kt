package br.com.joffer.mango.features.payment.creditcard.register

import br.com.joffer.mango.features.payment.contacts.Contact
import br.com.joffer.mango.features.payment.creditcard.CreditCard
import kotlinx.android.synthetic.main.activity_register_card.*

interface RegisterCardView {
    fun configureClickListeners()
    fun configureFields(creditCard: CreditCard)
    fun showSaveButton()
    fun hideSaveButton()
    fun configureCVVErro(cvvValid: Boolean)
    fun configureNumberErro(numberValid: Boolean)
    fun configureOwnerErro(ownerValid: Boolean)
    fun configureValidationErro(validationValid: Boolean)
    fun goToPayment(contact: Contact, creditCard: CreditCard)
}