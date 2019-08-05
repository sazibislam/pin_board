package com.sazib.pinboard.data.network

import com.sazib.pinboard.data.network.request.CityListRequest
import com.sazib.pinboard.data.network.request.CityWeatherRequest
import com.sazib.pinboard.data.network.response.CityWeatherResponse
import com.sazib.pinboard.data.network.response.WeatherDataResponse
import io.reactivex.Observable

interface ApiHelper {
  fun getApiHeader(): ApiHeader

  fun cityListApiCall(request: CityListRequest): Observable<WeatherDataResponse>

  fun cityWeatherApiCall(request: CityWeatherRequest): Observable<CityWeatherResponse>

}