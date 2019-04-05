package br.com.joffer.mango.features.payment.creditcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.joffer.mango.R
import br.com.joffer.mango.features.payment.creditcard.register.RegisterCardActivity
import kotlinx.android.synthetic.main.activity_card_priming.*

class CardPrimingActivity: AppCompatActivity(), CardPrimingView{

    companion object {
        fun starter(context: Context){
            context.startActivity(Intent(context, CardPrimingActivity::class.java))
        }
    }

    private val presenter: CardPrimingPresenter = CardPrimingPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_priming)
        presenter.onCreate()
    }

    override fun configureClickListeners(){
        back_button.setOnClickListener { onBackPressed() }
        register_new_card.setOnClickListener {
            RegisterCardActivity.starter(this)
        }
    }
}