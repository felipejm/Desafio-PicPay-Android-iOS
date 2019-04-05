package br.com.joffer.mango.features.payment.creditcard

class CardPrimingPresenterImpl(val view: CardPrimingView): CardPrimingPresenter{

    override fun onCreate(){
        view.configureClickListeners()
    }

}