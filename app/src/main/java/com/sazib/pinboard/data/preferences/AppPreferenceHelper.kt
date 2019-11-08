package com.sazib.pinboard.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.sazib.pinboard.di.PreferenceInfo
import com.sazib.pinboard.utils.AppConstants
import javax.inject.Inject

class AppPreferenceHelper @Inject constructor(
  context: Context, @PreferenceInfo prefFileName: String = AppConstants.PREF_NAME
) : PreferenceHelper {

  companion object {
    private const val PREF_KEY_ACCESS_TOKEN = "pref_key_access_token"
    private const val PREF_KEY_USER_ID = "pref_key_user_id"
  }

  private val mPrefs: SharedPreferences = context.getSharedPreferences(
      prefFileName, Context.MODE_PRIVATE
  )

  override fun getAccessToken(): String = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "") ?: ""

  override fun setAccessToken(accessToken: String) = mPrefs.edit().putString(
      PREF_KEY_ACCESS_TOKEN, accessToken
  ).apply()

  override fun getUserId(): String = mPrefs.getString(PREF_KEY_USER_ID, "") ?: ""

  override fun setUserId(userId: String) = mPrefs.edit().putString(PREF_KEY_USER_ID, userId).apply()

}