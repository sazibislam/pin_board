package com.sazib.pinboard.downloader.presenter

import com.androidnetworking.error.ANError
import com.sazib.pinboard.downloader.interactor.DownloadMVPInteractor
import com.sazib.pinboard.downloader.view.DownloadMVPView
import com.sazib.pinboard.ui.base.presenter.BasePresenter
import com.sazib.pinboard.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DownloadPresenter<V : DownloadMVPView, I : DownloadMVPInteractor> @Inject internal constructor(
  interactor: I,
  schedulerProvider: SchedulerProvider,
  disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable
), DownloadMVPPresenter<V, I> {

  override fun onAttach(view: V?) {
    super.onAttach(view)
    getView()?.apply {
      //initView()
    }
  }

  override fun download(url: String) {
    getView()?.let { view ->
      view.showProgress()
      val info = view.getDir()
      interactor?.apply {
        compositeDisposable.add(
            downloadFile(url, info.first.path, info.second.name)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ response ->
                  view.hideProgress()
                  response?.let { message -> view.showMessage(message) }
                      .also { /*view.openFile(info.second.name)*/ }
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