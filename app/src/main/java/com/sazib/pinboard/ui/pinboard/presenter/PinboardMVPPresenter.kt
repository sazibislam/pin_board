package com.sazib.pinboard.ui.pinboard.presenter

import com.sazib.pinboard.ui.base.presenter.MVPPresenter
import com.sazib.pinboard.ui.pinboard.interactor.PinboardMVPInteractor
import com.sazib.pinboard.ui.pinboard.view.PinboardMVPView

interface PinboardMVPPresenter<V : PinboardMVPView, I : PinboardMVPInteractor> : MVPPresenter<V, I> {

  fun getData(initRequest: Boolean)

  fun download(url: String)
}