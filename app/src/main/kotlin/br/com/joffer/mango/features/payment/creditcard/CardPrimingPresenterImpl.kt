package br.com.joffer.mango.features.payment.creditcard

import br.com.joffer.mango.features.payment.contacts.Contact
import java.io.Serializable

class CardPrimingPresenterImpl(val view: CardPrimingView): CardPrimingPresenter{

    private var contact: Contact? = null

    override fun onCreate(){
        view.configureClickListeners()
    }

    override fun onReceiveSerializable(serializableExtra: Serializable?) {
        contact = serializableExtra as Contact
    }

    override fun onRegisterNewClicked(){
        contact?.let {
            view.goToRegisterCard(it)
        }
    }
}