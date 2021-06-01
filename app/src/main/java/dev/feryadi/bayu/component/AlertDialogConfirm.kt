package dev.feryadi.bayu.component

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import dev.feryadi.bayu.databinding.AlertDialogConfirmLayoutBinding

class AlertDialogConfirm(private val viewBinding: AlertDialogConfirmLayoutBinding) {
    private val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(viewBinding.root.context)
        .setView(viewBinding.root)

    private var onClick: () -> Unit = {}

    fun setTitle(title: String): AlertDialogConfirm {
       viewBinding.tvAlertDialogTitle.text = title
       return this
    }

    fun setButtonTitle(title: String): AlertDialogConfirm {
        viewBinding.btnAlertDialogConfirm.text = title
        return this
    }

    fun setConfirmOnClickListener(onClick: () -> Unit): AlertDialogConfirm {
        this.onClick = onClick
        return this
    }

    fun setCancelable(cancelable: Boolean): AlertDialogConfirm {
        dialogBuilder.setCancelable(cancelable)
        return this
    }

    fun create(): AlertDialog {
        val dialog = dialogBuilder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        viewBinding.btnAlertDialogConfirm.setOnClickListener {
            dialog.dismiss()
            onClick()
        }

        return dialog
    }
}