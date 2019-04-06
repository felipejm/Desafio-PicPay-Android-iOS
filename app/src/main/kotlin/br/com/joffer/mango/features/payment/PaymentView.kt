package br.com.joffer.mango.features.payment

import br.com.joffer.mango.features.payment.contacts.Contact
import br.com.joffer.mango.features.payment.creditcard.CreditCard
import br.com.joffer.mango.features.payment.model.Transaction

interface PaymentView {
    fun configureContact(contact: Contact)
    fun configureCreditCard(creditCard: CreditCard)
    fun configureAmount()
    fun configureClickListeners()
    fun showLoading()
    fun hideLoading()
    fun showSuccess(transaction: Transaction, creditCard: CreditCard)
}