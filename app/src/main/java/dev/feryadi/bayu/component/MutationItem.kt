package dev.feryadi.bayu.component

import android.os.Build
import androidx.annotation.RequiresApi
import dev.feryadi.bayu.databinding.MutationItemLayoutBinding
import java.math.BigDecimal
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MutationItem(private val viewBinding: MutationItemLayoutBinding) {

    fun setAmount(amount: BigDecimal): MutationItem {
        val locale = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(locale)
        viewBinding.tvMutationItemLayoutMutationAmount.text = numberFormat.format(amount)
        return this
    }

    fun setDescription(description: String): MutationItem {
        viewBinding.tvMutationItemLayoutMutationDescription.text = description
        return this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCreatedDate(createdDate: String): MutationItem {
        val locale = Locale("in", "ID")
        val pattern = "dd MMM yyyy"
        val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, locale)
        val localDateTime = LocalDateTime.parse(createdDate, DateTimeFormatter.ISO_DATE_TIME)


        viewBinding.tvMutationItemLayoutMutationCreatedDate.text = dateTimeFormatter.format(localDateTime)
        return this
    }
}