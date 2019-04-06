package br.com.joffer.mango.features.payment.creditcard.register

import br.com.joffer.mango.features.payment.contacts.Contact
import br.com.joffer.mango.features.payment.creditcard.CreditCard
import br.com.joffer.mango.features.payment.creditcard.model.CardInteractor
import java.io.Serializable

class RegisterCardPresenterImpl(val view: RegisterCardView): RegisterCardPresenter{

    private val creditCard = CreditCard()
    private var contact: Contact? = null

    private val interactor = CardInteractor.instance

    override fun onCreate(){
        view.configureClickListeners()
        view.configureFields(creditCard)
    }

    override fun onReceiveSerializable(serializableExtra: Serializable?) {
        contact = serializableExtra as Contact
    }

    override fun onCardDataChanged() {
        val cvvValid = creditCard.isCVVValid()
        val ownerValid = creditCard.isOwnerValid()
        val validationValid = creditCard.isValidationValid()
        val numberValid = creditCard.isNumberValid()

        view.configureCVVErro(cvvValid)
        view.configureValidationErro(validationValid)
        view.configureNumberErro(numberValid)
        view.configureOwnerErro(ownerValid)

        if(cvvValid && ownerValid && validationValid && numberValid){
            view.showSaveButton()
        }else{
            view.hideSaveButton()
        }
    }

    override fun saveCard() {
        interactor.save(creditCard)
        contact?.let {
            view.goToPayment(it, creditCard)
        }
    }
}