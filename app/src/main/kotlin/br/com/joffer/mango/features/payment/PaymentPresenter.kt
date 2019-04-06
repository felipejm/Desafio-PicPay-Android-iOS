package br.com.joffer.mango.features.payment

import java.io.Serializable

interface PaymentPresenter {
    fun onCreate()
    fun onReceiveSerializable(serializable: Serializable?)
    fun onDestroy()
    fun pay(amount: String)
}