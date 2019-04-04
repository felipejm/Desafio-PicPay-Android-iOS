package br.com.joffer.mango.features.payment.contacts

interface ContactsView {
    fun configuRecyclerView(contacts: List<Contact>)
    fun showLoading()
    fun hideLoading()
}