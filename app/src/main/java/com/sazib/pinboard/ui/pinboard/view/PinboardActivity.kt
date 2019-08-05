package com.sazib.pinboard.ui.pinboard.view

import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.sazib.pinboard.R
import com.sazib.pinboard.ui.base.view.DaggerActivity
import com.sazib.pinboard.ui.pinboard.interactor.PinboardMVPInteractor
import com.sazib.pinboard.ui.pinboard.presenter.PinboardMVPPresenter
import javax.inject.Inject

class PinboardActivity : DaggerActivity(), PinboardMVPView {

  @Inject
  lateinit var presenter: PinboardMVPPresenter<PinboardMVPView, PinboardMVPInteractor>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
    setContentView(R.layout.activity_pinboard)
    presenter.onAttach(this)
  }

  override fun onDestroy() {
    presenter.onDetach()
    super.onDestroy()
  }

}
