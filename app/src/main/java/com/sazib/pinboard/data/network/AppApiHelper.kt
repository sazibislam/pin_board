package com.sazib.pinboard.data.network

import javax.inject.Inject

class AppApiHelper @Inject constructor(private val mApiHeader: ApiHeader) : ApiHelper {

  override fun getApiHeader(): ApiHeader = mApiHeader
}