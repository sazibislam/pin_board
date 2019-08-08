package com.sazib.pinboard.data.network

import com.sazib.pinboard.data.network.request.PinboardRequest
import com.sazib.pinboard.data.network.response.PinboardResponse
import io.reactivex.Observable

interface ApiHelper {

  fun getApiHeader(): ApiHeader

  fun pinboardApiCall(request: PinboardRequest): Observable<List<PinboardResponse>>

  fun downloadFile(
    url: String,
    dirPath: String,
    fileName: String
  ): Observable<String>

}