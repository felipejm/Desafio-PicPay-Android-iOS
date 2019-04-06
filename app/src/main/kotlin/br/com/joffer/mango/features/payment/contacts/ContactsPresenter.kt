package br.com.joffer.mango.features.payment.contacts

interface ContactsPresenter {
    fun onCreate()
    fun onDestroy()
    fun onContactClicked(contact: Contact)
}