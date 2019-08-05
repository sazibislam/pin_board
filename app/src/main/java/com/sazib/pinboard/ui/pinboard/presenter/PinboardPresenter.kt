package com.sazib.pinboard.ui.pinboard.presenter

import com.sazib.pinboard.ui.base.presenter.BasePresenter
import com.sazib.pinboard.ui.pinboard.interactor.PinboardMVPInteractor
import com.sazib.pinboard.ui.pinboard.view.PinboardMVPView
import com.sazib.pinboard.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PinboardPresenter<V : PinboardMVPView, I : PinboardMVPInteractor> @Inject internal constructor(
  interactor: I,
  schedulerProvider: SchedulerProvider,
  disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable
), PinboardMVPPresenter<V, I> {

}