package com.sazib.pinboard.ui.pinboard.interactor

import com.sazib.pinboard.data.network.ApiHelper
import com.sazib.pinboard.data.network.request.PinboardRequest
import com.sazib.pinboard.data.network.response.PinboardResponse
import com.sazib.pinboard.data.preferences.PreferenceHelper
import com.sazib.pinboard.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class PinboardInteractor @Inject constructor(
  preferenceHelper: PreferenceHelper,
  apiHelper: ApiHelper
) : BaseInteractor(preferenceHelper, apiHelper), PinboardMVPInteractor {

  override fun pinBoardApiCall(request: PinboardRequest): Observable<List<PinboardResponse>> =
    apiHelper.pinboardApiCall(request)
}