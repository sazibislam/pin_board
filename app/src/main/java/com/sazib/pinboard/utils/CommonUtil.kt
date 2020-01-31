package com.sazib.pinboard.utils

import android.app.Dialog
import android.content.Context
import com.sazib.pinboard.R

object CommonUtil {
  fun showLoadingDialog(context: Context): Dialog {
    val dialog = Dialog(context)
    dialog.show()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.setContentView(R.layout.progress_dialog)
    dialog.setCancelable(false)
    dialog.setCanceledOnTouchOutside(false)
    return dialog
  }
}