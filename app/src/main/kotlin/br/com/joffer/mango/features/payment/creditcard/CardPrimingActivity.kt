package br.com.joffer.mango.features.payment.creditcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.joffer.mango.R
import br.com.joffer.mango.features.payment.PaymentActivity
import br.com.joffer.mango.features.payment.contacts.Contact
import br.com.joffer.mango.features.payment.creditcard.register.RegisterCardActivity
import kotlinx.android.synthetic.main.activity_card_priming.*

class CardPrimingActivity: AppCompatActivity(), CardPrimingView{

    companion object {
        private const val CONTACT = "CONTACT"
        fun starter(context: Context, contact: Contact){
            val intent = Intent(context, CardPrimingActivity::class.java)
            intent.putExtra(CONTACT, contact)
            context.startActivity(intent)
        }
    }

    private val presenter: CardPrimingPresenter = CardPrimingPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_priming)

        presenter.onReceiveSerializable(intent?.getSerializableExtra(CONTACT))
        presenter.onCreate()
    }

    override fun configureClickListeners(){
        back_button.setOnClickListener { onBackPressed() }
        register_new_card.setOnClickListener {
            presenter.onRegisterNewClicked()
        }
    }

    override fun goToRegisterCard(contact: Contact){
        RegisterCardActivity.starter(this, contact)
    }
}