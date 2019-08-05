package com.sazib.pinboard.ui.base.view

import android.app.AlertDialog
import android.app.Dialog
import android.app.Service
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.text.toSpanned
import com.sazib.pinboard.R
import com.sazib.pinboard.utils.CommonUtil
import com.sazib.pinboard.utils.NetworkUtils
import com.sazib.pinboard.utils.logger.AppLogger
import dagger.android.AndroidInjection

abstract class BasePinboardService : Service(), ServiceMVPView {

  private var dialog: Dialog? = null
  private var toast: Toast? = null

  override fun onCreate() {
    performDependencyInjection()
    super.onCreate()
  }

  private fun performDependencyInjection() = AndroidInjection.inject(this)

  override fun hideProgress() = dialog?.let { dialog_ -> if (dialog_.isShowing) dialog_.cancel() }

  override fun showProgress() {
    hideProgress()
    dialog = CommonUtil.showLoadingDialog(this)
  }

  override fun setupToolbar(
    toolbar: Toolbar,
    title: String?
  ) {

  }

  override fun setupToolbarBack(
    toolbar: Toolbar,
    title: String?
  ) {

  }

  override fun setupToolbarBack(toolbar: Toolbar) {

  }

  override fun hideKeyboard() {
    /*val view = this.application.currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }*/
  }

  override fun showKeyboard() {
    /*val view = this.currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }*/
  }

  override fun finishAction(): DialogInterface.OnClickListener = FinishAction()

  override fun getResString(resString: Int): String = getString(resString)

  override fun onError(message: String?) = showMessage(message)

  override fun onError(@StringRes resId: Int) = onError(getString(resId))

  override fun showMessage(message: String?) {
    toast?.cancel()

    val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout: View = layoutInflater.inflate(R.layout.toast_layout, null)

    val text = layout.findViewById<TextView>(R.id.text)
    text.text = message?.toSpanned() ?: "Some Error Occurred!"

    toast = Toast(this)
    toast?.duration = Toast.LENGTH_SHORT
    toast?.view = layout
    toast?.show()
  }

  override fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

  override fun isNetworkConnected(): Boolean = NetworkUtils.isNetworkConnected(applicationContext)

  override fun finishIt() {}

  private fun makeAlert(
    title: String?,
    message: String,
    okListener: DialogInterface.OnClickListener?,
    noListener: DialogInterface.OnClickListener?
  ) {
    try {
      val builder = AlertDialog.Builder(this)

      if (title != null) {
        builder.setTitle(title)
      }
      builder.setCancelable(false)
      builder.setMessage(message.toSpanned())

      if (okListener != null) {
        builder.setPositiveButton(android.R.string.ok, okListener)
      }

      if (noListener != null) {
        builder.setNegativeButton(android.R.string.cancel, noListener)
      }

      val alert = builder.create()
      alert.setCancelable(false)
      alert.show()
    } catch (e: Exception) {
      AppLogger.e("showMessage: %s", e.message)
    }
  }

  override fun showAlert(
    title: Int,
    message: String,
    okListener: DialogInterface.OnClickListener
  ) = makeAlert(getString(title), message, okListener, null)

  override fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener
  ) = makeAlert(title, message, okListener, null)

  override fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener,
    noListener: DialogInterface.OnClickListener
  ) = makeAlert(title, message, okListener, noListener)

  override fun showAlert(
    title: Int,
    message: String
  ) = makeAlert(getString(title), message, NoAction(), null)

  override fun showAlert(message: String) = makeAlert(null, message, NoAction(), null)

  override fun showAlert(message: Int) =
    makeAlert(null, getString(message), NoAction(), null)

  override fun showAlert(
    message: String,
    okListener: DialogInterface.OnClickListener
  ) = makeAlert(null, message, okListener, NoAction())

  override fun showAlert(
    message: Int,
    okListener: DialogInterface.OnClickListener
  ) = makeAlert(null, getString(message), okListener, null)

  override fun error() {
    showMessage(R.string.general_error)
  }

  override fun error(message: String?) {
    message?.let { message_ ->
      showMessage(message_)
    } ?: showMessage(R.string.general_error)
  }

  internal inner class FinishAction : DialogInterface.OnClickListener {
    override fun onClick(
      dialog: DialogInterface,
      which: Int
    ) {

    }
  }

  internal inner class NoAction : DialogInterface.OnClickListener {
    override fun onClick(
      dialog: DialogInterface,
      which: Int
    ) {

    }
  }
}