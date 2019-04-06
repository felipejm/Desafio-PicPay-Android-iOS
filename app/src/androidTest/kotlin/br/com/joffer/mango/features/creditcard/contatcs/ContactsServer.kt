package br.com.joffer.mango.features.creditcard.contatcs

import br.com.joffer.mango.features.payment.contacts.model.ContactApi
import br.com.joffer.mango.features.payment.contacts.model.ContactInteractor
import br.com.joffer.mango.features.payment.contacts.model.ContactRepository
import br.com.joffer.mango.infra.RetrofitService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

object ContactsServer{

    private val contactJson = "[\n" +
            "  {\n" +
            "    \"id\":1001,\n" +
            "    \"name\":\"Eduardo Santos\",\n" +
            "    \"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\n" +
            "    \"username\":\"@eduardo.santos\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":1002,\n" +
            "    \"name\":\"Marina Coelho\",\n" +
            "    \"img\":\"https://randomuser.me/api/portraits/women/37.jpg\",\n" +
            "    \"username\":\"@marina.coelho\"\n" +
            "  }\n" +
            "]"

    fun start(){
        val serverUrl = startServer()
        configureMockServer(serverUrl)
    }

    private fun startServer(): String {
        val server = MockWebServer()
        server.enqueue(MockResponse().setBody(contactJson))
        server.start()
        return server.url("").toString()
    }

    private fun configureMockServer(serverUrl: String) {
        val service = RetrofitService(ContactApi::class.java, serverUrl).apiService

        val interactor = ContactInteractor.instance
        val fieldRepository = interactor::class.java.getDeclaredField("repository")
        fieldRepository.isAccessible = true

        val repository = fieldRepository.get(interactor)

        val fieldService = ContactRepository::class.java.getDeclaredField("service")
        fieldService.isAccessible = true
        fieldService.set(repository, service)
    }

}