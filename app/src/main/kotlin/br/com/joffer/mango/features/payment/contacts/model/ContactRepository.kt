package br.com.joffer.mango.features.payment.contacts.model

import br.com.joffer.mango.BuildConfig
import br.com.joffer.mango.features.payment.contacts.Contact
import br.com.joffer.mango.infra.RetrofitService
import io.reactivex.Observable

class ContactRepository: ContactInteractor{

    private val service by lazy { RetrofitService(ContactApi::class.java, BuildConfig.BASE_URL).apiService }

    override fun fetchContacts(): Observable<List<Contact>>{
        return service.fetchContacts()
    }

}