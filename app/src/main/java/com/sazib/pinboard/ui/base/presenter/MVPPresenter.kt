package com.sazib.pinboard.ui.base.presenter

import com.androidnetworking.error.ANError
import com.sazib.pinboard.ui.base.interactor.MVPInteractor
import com.sazib.pinboard.ui.base.view.MVPView

interface MVPPresenter<V : MVPView, I : MVPInteractor> {

  fun onAttach(view: V?)

  fun onDetach()

  fun getView(): V?

  fun handleApiError(error: ANError?)

}