package dev.feryadi.bayu.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import dev.feryadi.bayu.R
import dev.feryadi.bayu.databinding.FeaturedMenuLayoutBinding

class FeaturedIMenu(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    val viewBinding = FeaturedMenuLayoutBinding.inflate(
        LayoutInflater.from(context), this, true
    )


    init {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.FeaturedIMenu, 0, 0
        )

        val icon = typedArray.getDrawable(R.styleable.FeaturedIMenu_icon)

        val title = typedArray.getText(R.styleable.FeaturedIMenu_title)
        val titleTextColor = typedArray.getColor(R.styleable.FeaturedIMenu_titleTextColor, viewBinding.tvFeaturedMenuTitle.currentTextColor)

        icon?.let { setIcon(it) }
        setTitle(title)
        setTitleTextColor(titleTextColor)
    }

    fun setOnClickListener(onClick: () -> Unit) {
        viewBinding.root.setOnClickListener {
            onClick()
        }
    }

    fun setIcon(drawable: Drawable) {
        viewBinding.ivFeaturedMenuIcon.background = drawable
    }

    fun setIconTint(color: Int) {
        viewBinding.ivFeaturedMenuIcon.backgroundTintList = context.getColorStateList(color)
    }

    fun setTitle(value: CharSequence) {
        viewBinding.tvFeaturedMenuTitle.text = value
    }

    fun setTitleTextColor(color: Int) {
        viewBinding.tvFeaturedMenuTitle.setTextColor(color)
    }


}