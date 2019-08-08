package com.sazib.pinboard.downloader.interactor

import com.sazib.pinboard.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface DownloadMVPInteractor : MVPInteractor {

  fun downloadFile(
    url: String,
    dirPath: String,
    fileName: String
  ): Observable<String>
}