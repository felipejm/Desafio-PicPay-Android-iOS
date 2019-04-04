package br.com.joffer.mango.features.payment.contacts.model

import br.com.joffer.mango.features.payment.contacts.Contact
import io.reactivex.Observable
import retrofit2.http.GET

interface ContactApi{

    @GET("users")
    fun fetchContacts(): Observable<List<Contact>>
}