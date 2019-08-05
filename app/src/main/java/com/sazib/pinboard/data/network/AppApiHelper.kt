package com.sazib.pinboard.data.network

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.sazib.pinboard.data.network.request.PinboardRequest
import com.sazib.pinboard.data.network.response.PinboardResponse
import io.reactivex.Observable
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val mApiHeader: ApiHeader) : ApiHelper {

  override fun getApiHeader(): ApiHeader = mApiHeader

  override fun pinboardApiCall(request: PinboardRequest): Observable<PinboardResponse> =

    Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PINBOARD_DATA)
            .addHeaders(mApiHeader.authApiHeader)
            .addQueryParameter(request)
            .build()
            .getObjectObservable(PinboardResponse::class.java)
}