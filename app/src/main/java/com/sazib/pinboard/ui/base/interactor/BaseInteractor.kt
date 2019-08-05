package com.sazib.pinboard.ui.base.interactor

import com.sazib.pinboard.data.network.ApiHelper
import com.sazib.pinboard.data.preferences.PreferenceHelper

open class BaseInteractor() : MVPInteractor {

  protected lateinit var preferenceHelper: PreferenceHelper
  protected lateinit var apiHelper: ApiHelper

  constructor(
    preferenceHelper: PreferenceHelper,
    apiHelper: ApiHelper
  ) : this() {
    this.preferenceHelper = preferenceHelper
    this.apiHelper = apiHelper
  }

  override fun getUserId(): String = preferenceHelper.getUserId()

  override fun getToken(): String = preferenceHelper.getAccessToken()

}