package br.com.joffer.mango.features.payment.creditcard

import java.io.Serializable

interface CardPrimingPresenter {
    fun onCreate()
    fun onReceiveSerializable(serializableExtra: Serializable?)
    fun onRegisterNewClicked()
}