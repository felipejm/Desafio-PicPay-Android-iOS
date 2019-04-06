package br.com.joffer.mango.infra.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardHelper{

    fun hideSoftKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}