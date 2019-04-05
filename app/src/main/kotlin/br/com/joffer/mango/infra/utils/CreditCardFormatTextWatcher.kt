package br.com.joffer.mango.infra.utils


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.ReplacementSpan
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.NonNull

class CreditCardFormatTextWatcher : TextWatcher {
    private var maxLength = NO_MAX_LENGTH
    private var paddingPx: Int = 0
    private var internalStopFormatFlag: Boolean = false


    constructor(paddingPx: Int) {
        setPaddingPx(paddingPx)
    }


    constructor(@NonNull textView: TextView) {
        setPaddingEm(textView, 1f)
    }


    constructor(@NonNull textView: TextView, paddingEm: Float) {
        setPaddingEm(textView, paddingEm)
    }


    constructor(@NonNull context: Context, paddingSp: Float) {
        setPaddingSp(context, paddingSp)
    }


    fun setPaddingPx(paddingPx: Int) {
        this.paddingPx = paddingPx
    }


    fun setPaddingEm(@NonNull textView: TextView, em: Float) {
        val emSize = textView.paint.measureText("x")
        setPaddingPx((em * emSize).toInt())
    }


    fun setPaddingSp(@NonNull context: Context, paddingSp: Float) {
        setPaddingPx(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                paddingSp,
                context.resources.displayMetrics
            ).toInt()
        )
    }

    fun setMaxLength(maxLength: Int) {
        this.maxLength = maxLength
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {
        if (internalStopFormatFlag) {
            return
        }
        internalStopFormatFlag = true
        formatCardNumber(s, paddingPx, maxLength)
        internalStopFormatFlag = false
    }

    fun formatCardNumber(@NonNull textView: TextView) {
        afterTextChanged(textView.editableText)
    }

    class PaddingRightSpan(private val mPadding: Int) : ReplacementSpan() {

        override fun getSize(
            @NonNull paint: Paint, text: CharSequence,
            start: Int,
            end: Int,
            fm: Paint.FontMetricsInt?
        ): Int {
            val widths = FloatArray(end - start)
            paint.getTextWidths(text, start, end, widths)
            var sum = mPadding
            for (i in widths.indices) {
                sum += widths[i].toInt()
            }
            return sum
        }

        override fun draw(
            @NonNull canvas: Canvas, text: CharSequence,
            start: Int,
            end: Int,
            x: Float,
            top: Int,
            y: Int,
            bottom: Int, @NonNull paint: Paint
        ) {
            canvas.drawText(text, start, end, x, y.toFloat(), paint)
        }

    }

    companion object {
        val NO_MAX_LENGTH = -1

        @JvmOverloads
        fun formatCardNumber(@NonNull ccNumber: Editable, paddingPx: Int, maxLength: Int = NO_MAX_LENGTH) {
            val textLength = ccNumber.length
            val spans = ccNumber.getSpans(0, ccNumber.length, PaddingRightSpan::class.java)
            for (i in spans.indices) {
                ccNumber.removeSpan(spans[i])
            }
            if (maxLength > 0 && textLength > maxLength - 1) {
                ccNumber.replace(maxLength, textLength, "")
            }
            for (i in 1..(textLength - 1) / 4) {
                val end = i * 4
                val start = end - 1
                val marginSPan = PaddingRightSpan(paddingPx)
                ccNumber.setSpan(marginSPan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }
}