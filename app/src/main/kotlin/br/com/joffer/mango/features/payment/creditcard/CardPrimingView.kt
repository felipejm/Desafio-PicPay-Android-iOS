package br.com.joffer.mango.features.payment.creditcard

import br.com.joffer.mango.features.payment.contacts.Contact

interface CardPrimingView {
    fun configureClickListeners()
    fun goToRegisterCard(contact: Contact)
}