package com.sazib.pinboard.data.network

import com.sazib.pinboard.BuildConfig

object ApiEndPoint {

  private const val BASE_URL = BuildConfig.BASE_URL

  const val ENDPOINT_PINBOARD_DATA = "$BASE_URL/raw"

}