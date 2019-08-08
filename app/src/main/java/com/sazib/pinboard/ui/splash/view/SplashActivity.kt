package com.sazib.pinboard.ui.splash.view

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.sazib.pinboard.R
import com.sazib.pinboard.ui.base.view.DaggerActivity
import com.sazib.pinboard.ui.pinboard.view.PinboardActivity
import com.sazib.pinboard.ui.splash.interactor.SplashMVPInteractor
import com.sazib.pinboard.ui.splash.presenter.SplashMVPPresenter
import com.sazib.pinboard.utils.AppConstants
import javax.inject.Inject

class SplashActivity : DaggerActivity(), SplashMVPView {

  @Inject lateinit var presenter: SplashMVPPresenter<SplashMVPView, SplashMVPInteractor>

  private var mHandler: Handler? = null
  private var mRunnable: Runnable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
    setContentView(R.layout.activity_splash)
    presenter.onAttach(this)
  }

  /**
   * Instantiation of Handler, RunSplash
   * **/
  override fun start() {
    mHandler = Handler()
    mRunnable = RunSplash()
    mHandler?.postDelayed(mRunnable, AppConstants.SPLASH_TIME_OUT)
  }

  /**
   * Destroying the presenter
   * **/
  override fun onDestroy() {
    mHandler?.removeCallbacks(mRunnable)
    presenter.onDetach()
    super.onDestroy()
  }

  /**
   * Starting the PinBoard activity
   * **/
  private inner class RunSplash internal constructor() : Runnable {
    override fun run() {
      startActivity(PinboardActivity.getStartIntent(applicationContext))
      finishIt()
    }
  }
}
