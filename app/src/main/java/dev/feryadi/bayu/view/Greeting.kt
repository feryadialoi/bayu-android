package dev.feryadi.bayu.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import dev.feryadi.bayu.R
import dev.feryadi.bayu.databinding.GreetingLayoutBinding
import dev.feryadi.bayu.utils.UnitUtil

class Greeting(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    val viewBinding: GreetingLayoutBinding = GreetingLayoutBinding.inflate(
        LayoutInflater.from(context), this, true
    )


    init {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.Greeting, 0, 0
        )

        val message: CharSequence = typedArray.getText(R.styleable.Greeting_message)
        val name: CharSequence = typedArray.getText(R.styleable.Greeting_name)
        val messageColor = typedArray.getColor(
            R.styleable.Greeting_messageColor,
            viewBinding.tvGreetingMessage.currentTextColor
        )
        val nameColor = typedArray.getColor(
            R.styleable.Greeting_nameColor,
            viewBinding.tvGreetingName.currentTextColor
        )
        val messageSize = typedArray.getDimensionPixelSize(R.styleable.Greeting_messageSize, 0)
        val nameSize = typedArray.getDimensionPixelSize(R.styleable.Greeting_nameSize, 0)

        typedArray.recycle()

        setMessage(message)
        setName(name)
        setMessageColor(messageColor)
        setNameColor(nameColor)


        setMessageSize(TypedValue.COMPLEX_UNIT_SP, UnitUtil.pxToSp(messageSize))
        setNameSize(TypedValue.COMPLEX_UNIT_SP, UnitUtil.pxToSp(nameSize))
    }

    fun getMessage(): CharSequence {
        return viewBinding.tvGreetingMessage.text
    }

    fun setMessage(value: CharSequence) {
        viewBinding.tvGreetingMessage.text = value
    }

    fun getName(): CharSequence {
        return viewBinding.tvGreetingName.text
    }

    fun setName(value: CharSequence) {
        viewBinding.tvGreetingName.text = value
    }

    fun setMessageColor(color: Int) {
        viewBinding.tvGreetingMessage.setTextColor(color)
    }

    fun setNameColor(color: Int) {
        viewBinding.tvGreetingName.setTextColor(color)
    }

    fun setMessageSize(size: Float) {
        viewBinding.tvGreetingMessage.setTextSize(size)
    }

    fun setMessageSize(unit: Int, size: Float) {
        viewBinding.tvGreetingMessage.setTextSize(unit, size)
    }

    fun setNameSize(size: Float) {
        viewBinding.tvGreetingName.setTextSize(size)
    }

    fun setNameSize(unit: Int, size: Float) {
        viewBinding.tvGreetingName.setTextSize(unit, size)
    }
}