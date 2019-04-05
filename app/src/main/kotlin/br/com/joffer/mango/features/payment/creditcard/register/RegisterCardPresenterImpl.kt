package br.com.joffer.mango.features.payment.creditcard.register

import br.com.joffer.mango.features.payment.creditcard.CreditCard
import br.com.joffer.mango.features.payment.creditcard.model.CardInteractor

class RegisterCardPresenterImpl(val view: RegisterCardView): RegisterCardPresenter{

    private val creditCard = CreditCard()
    private val interactor = CardInteractor.instance

    override fun onCreate(){
        view.configureClickListeners()
        view.configureFields(creditCard)
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
        view.goToPayment()
    }
}