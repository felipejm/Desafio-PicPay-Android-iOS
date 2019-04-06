package br.com.joffer.mango.features.payment.creditcard.register

import java.io.Serializable

interface RegisterCardPresenter {
    fun onCreate()
    fun saveCard()
    fun onCardDataChanged()
    fun onReceiveSerializable(serializableExtra: Serializable?)
}