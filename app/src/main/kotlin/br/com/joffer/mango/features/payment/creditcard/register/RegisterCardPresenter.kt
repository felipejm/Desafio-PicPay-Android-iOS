package br.com.joffer.mango.features.payment.creditcard.register

interface RegisterCardPresenter {
    fun onCreate()
    fun saveCard()
    fun onCardDataChanged()
}