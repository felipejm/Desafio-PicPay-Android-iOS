package br.com.joffer.mango.features.payment.receipt

import android.app.Activity
import android.view.View
import br.com.joffer.mango.R
import br.com.joffer.mango.features.payment.creditcard.CreditCard
import br.com.joffer.mango.features.payment.model.Transaction
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.view_receipt.view.*
import java.text.SimpleDateFormat
import java.util.*

object ReceiptBottomSheet{

    fun showReceipt(activity: Activity, transaction: Transaction, creditCard: CreditCard){
        val mBottomSheetDialog = BottomSheetDialog(activity)
        val sheetView = activity.layoutInflater.inflate(R.layout.view_receipt, null)

        configureView(sheetView, transaction, creditCard)
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
    }

    private fun configureView(sheetView: View, transaction: Transaction, creditCard: CreditCard){
        Glide.with(sheetView.context)
            .load(transaction.contact.img)
            .apply(RequestOptions().circleCrop())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(sheetView.avatar)

        val cardNumber = creditCard.number.takeLast(4)
        sheetView.card_name.text = "Cartão Mastercard $cardNumber"

        sheetView.user_name.text = transaction.contact.name
        sheetView.total_amount.text = "R$ ${transaction.amount}"
        sheetView.card_amount.text = "R$ ${transaction.amount}"
        sheetView.transaction.text = transaction.id

        val dateFormat = SimpleDateFormat("dd/MM/yyyy 'às hh:mm'")
        sheetView.date.text = dateFormat.format(Date(transaction.timestamp))

    }
}