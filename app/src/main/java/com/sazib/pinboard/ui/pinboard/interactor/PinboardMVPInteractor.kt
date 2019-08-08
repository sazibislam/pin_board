package com.sazib.pinboard.ui.pinboard.interactor

import com.sazib.pinboard.data.network.request.PinboardRequest
import com.sazib.pinboard.data.network.response.PinboardResponse
import com.sazib.pinboard.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface PinboardMVPInteractor : MVPInteractor {

  fun pinBoardApiCall(request: PinboardRequest): Observable<List<PinboardResponse>>

  fun downloadFile(
    url: String,
    dirPath: String,
    fileName: String
  ): Observable<String>

}