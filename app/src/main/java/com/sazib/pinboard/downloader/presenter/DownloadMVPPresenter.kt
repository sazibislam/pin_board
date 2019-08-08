package com.sazib.pinboard.downloader.presenter

import com.sazib.pinboard.downloader.interactor.DownloadMVPInteractor
import com.sazib.pinboard.downloader.view.DownloadMVPView
import com.sazib.pinboard.ui.base.presenter.MVPPresenter

interface DownloadMVPPresenter<V : DownloadMVPView, I : DownloadMVPInteractor> :
    MVPPresenter<V, I> {

  fun download(url: String)
}