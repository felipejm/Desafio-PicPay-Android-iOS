package br.com.joffer.mango.infra

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import br.com.joffer.mango.R
import br.com.joffer.mango.infra.utils.dpToPx

class SearchEditText: AppCompatEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)

        if(focused){
            val searchIcon = ContextCompat.getDrawable(context, R.drawable.ic_search)
            val closeIcon = ContextCompat.getDrawable(context, R.drawable.ic_close)
            setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, closeIcon, null)
        }else{
            val searchIcon = ContextCompat.getDrawable(context, R.drawable.ic_search)
            setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null)
        }
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            clearFocus()
        }
        return super.onKeyPreIme(keyCode, event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val drawable = compoundDrawables[2]
        if (drawable != null) {
            if (event?.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (right - drawable.bounds.width() - paddingRight))
                setText("")
            }
        }
        return super.onTouchEvent(event)
    }
}