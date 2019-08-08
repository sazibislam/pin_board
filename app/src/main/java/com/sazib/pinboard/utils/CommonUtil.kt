package com.sazib.pinboard.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import com.sazib.pinboard.R

object CommonUtil {
  fun showLoadingDialog(context: Context): Dialog {
    val dialog = Dialog(context)
    dialog.show()
    dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
    dialog.setContentView(R.layout.progress_dialog)
    dialog.setCancelable(false)
    dialog.setCanceledOnTouchOutside(false)
    return dialog
  }
}