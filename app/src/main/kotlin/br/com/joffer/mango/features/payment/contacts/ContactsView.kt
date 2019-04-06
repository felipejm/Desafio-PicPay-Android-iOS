package br.com.joffer.mango.features.payment.contacts

import br.com.joffer.mango.features.payment.creditcard.CreditCard

interface ContactsView {
    fun configuRecyclerView(contacts: List<Contact>)
    fun showLoading()
    fun hideLoading()
    fun goToPayment(contact: Contact, creditCard: CreditCard)
    fun goToCardPriming(contact: Contact)
}