package br.com.joffer.mango.features.payment.contacts

import android.util.Log
import br.com.joffer.mango.features.payment.contacts.model.ContactInteractor
import br.com.joffer.mango.infra.utils.onBackgroundThread
import io.reactivex.disposables.Disposable

class ContactsPresenterImpl(val view: ContactsView): ContactsPresenter{

    private val interactor = ContactInteractor.instance
    private var disposable: Disposable? = null

    override fun onCreate(){
        loadContacts()
    }

    override fun onDestroy(){
        disposable?.dispose()
    }

    private fun loadContacts(){
        disposable = interactor.fetchContacts()
            .onBackgroundThread()
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe({
                view.configuRecyclerView(it)
            }, {
                Log.e("ContactsPresenter", it.message, it)
            })
    }
}