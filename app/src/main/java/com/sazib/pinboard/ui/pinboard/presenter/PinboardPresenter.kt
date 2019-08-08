package com.sazib.pinboard.ui.pinboard.presenter

import com.androidnetworking.error.ANError
import com.sazib.pinboard.data.network.request.PinboardRequest
import com.sazib.pinboard.ui.base.presenter.BasePresenter
import com.sazib.pinboard.ui.pinboard.interactor.PinboardMVPInteractor
import com.sazib.pinboard.ui.pinboard.view.PinboardMVPView
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

    //getView()?.setupData(AppDataUtils.getPinBoardData())

    getData()
  }

  override fun getData() {
    getView()?.showProgress()
    getView()?.apply {
      if (!isNetworkConnected()) return
      interactor?.apply {
        compositeDisposable.add(
            pinBoardApiCall(
                //PinboardRequest(getToken()),
                PinboardRequest("")
            ).compose(SchedulerProvider().ioToMainObservableScheduler())
                .subscribe({ response ->
                  getView()?.hideProgress()
                  response?.let { data_ ->
                    getView()?.setupData(data_)
                    AppLogger.d(data_)
                  }
                }, { throwable ->
                  run {
                    val anError = throwable as ANError
                    handleApiError(anError)
                  }
                })
        )
      }
    }
  }

  override fun download(url: String) {
    getView()?.let { view ->
      view.showProgress()
      val info = view.getDefultDir()
      interactor?.apply {
        compositeDisposable.add(
            downloadFile(url, info.first.path, info.second.name)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ response ->
                  view.hideProgress()
                  response?.let { message -> view.showMessage(message) }
                  //.also { view.openPdfFile(info.second.name) }
                }, { throwable ->
                  run {
                    val anError = throwable as ANError
                    handleApiError(anError)
                  }
                })
        )
      }
    }
  }

}