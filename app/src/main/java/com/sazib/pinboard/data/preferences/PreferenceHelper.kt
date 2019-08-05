package com.sazib.pinboard.data.preferences

interface PreferenceHelper {

  fun getAccessToken(): String

  fun setAccessToken(accessToken: String)

  fun getUserId(): String

  fun setUserId(userId: String)

  fun getAppId(): String?

  fun setAppId(id: String)

}