package com.sazib.pinboard.ui.pinboard.presenter

import com.sazib.pinboard.data.network.request.PinboardRequest
import com.sazib.pinboard.ui.base.presenter.BasePresenter
import com.sazib.pinboard.ui.pinboard.interactor.PinboardMVPInteractor
import com.sazib.pinboard.ui.pinboard.view.PinboardMVPView
import com.sazib.pinboard.utils.AppDataUtils
import com.sazib.pinboard.utils.SchedulerProvider
import com.sazib.pinboard.utils.logger.AppLogger
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PinboardPresenter<V : PinboardMVPView, I : PinboardMVPInteractor> @Inject internal constructor(
  interactor: I,
  schedulerProvider: SchedulerProvider,
  disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable
), PinboardMVPPresenter<V, I> {

  override fun onAttach(view: V?) {
    super.onAttach(view)
    getView()?.initView()

    getView()?.setupData(AppDataUtils.getPinBoardData())

  }

  override fun getData() {

    getView()?.apply {
      if (!isNetworkConnected()) return
      interactor?.apply {
        compositeDisposable.add(
            pinBoardApiCall(
                PinboardRequest(getUserId())
            ).compose(SchedulerProvider().ioToMainObservableScheduler())
                .subscribe({ response -> AppLogger.d(response) }, { })
        )
      }
    }
  }
}