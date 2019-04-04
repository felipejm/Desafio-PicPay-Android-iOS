package br.com.joffer.mango.features.payment.contacts.model

import br.com.joffer.mango.features.payment.contacts.Contact
import io.reactivex.Observable

class ContactInteractorImpl: ContactInteractor{

    private val repository: ContactRepository = ContactRepository()

    override fun fetchContacts(): Observable<List<Contact>> {
        return repository.fetchContacts()
    }
}