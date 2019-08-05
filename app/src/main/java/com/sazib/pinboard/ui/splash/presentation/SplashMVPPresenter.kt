package com.sazib.pinboard.ui.splash.presentation

import com.sazib.pinboard.ui.base.presenter.MVPPresenter
import com.sazib.pinboard.ui.splash.interactor.SplashMVPInteractor
import com.sazib.pinboard.ui.splash.view.SplashMVPView

interface SplashMVPPresenter<V : SplashMVPView, I : SplashMVPInteractor> : MVPPresenter<V, I>