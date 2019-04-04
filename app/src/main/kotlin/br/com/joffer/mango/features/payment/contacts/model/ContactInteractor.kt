package br.com.joffer.mango.features.payment.contacts.model

import br.com.joffer.mango.features.payment.contacts.Contact
import io.reactivex.Observable

interface ContactInteractor{
    companion object {
        val instance: ContactInteractor by lazy { ContactInteractorImpl() }
    }

    fun fetchContacts(): Observable<List<Contact>>
}