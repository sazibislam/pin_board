package com.sazib.pinboard.downloader.interactor

import com.sazib.pinboard.data.network.ApiHelper
import com.sazib.pinboard.data.preferences.PreferenceHelper
import com.sazib.pinboard.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class DownloadInteractor @Inject constructor(
  preferenceHelper: PreferenceHelper,
  apiHelper: ApiHelper
) : BaseInteractor(preferenceHelper, apiHelper), DownloadMVPInteractor {

  override fun downloadFile(
    url: String,
    dirPath: String,
    fileName: String
  ): Observable<String> = apiHelper.downloadFile(url, dirPath, fileName)

}
