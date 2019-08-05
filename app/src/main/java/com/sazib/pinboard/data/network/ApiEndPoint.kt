package com.sazib.pinboard.data.network

import com.sazib.pinboard.BuildConfig

object ApiEndPoint {

  private const val BASE_URL = BuildConfig.BASE_URL

  const val ENDPOINT_CITY_DATA = "$BASE_URL/data/2.5/find"

  const val ENDPOINT_CITY_WEATHER_DATA = "$BASE_URL/data/2.5/weather"

}