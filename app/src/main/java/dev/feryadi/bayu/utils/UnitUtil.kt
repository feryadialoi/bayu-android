package dev.feryadi.bayu.utils

import android.content.res.Resources

object UnitUtil {
    fun spToPx(sp: Int): Int {
        return (sp * Resources.getSystem().displayMetrics.scaledDensity).toInt()
    }

    fun pxToSp(px: Int): Float {
        return (px / Resources.getSystem().displayMetrics.scaledDensity)
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }
}