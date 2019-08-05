package com.sazib.pinboard.ui.splash.interactor

import com.sazib.pinboard.data.network.ApiHelper
import com.sazib.pinboard.data.preferences.PreferenceHelper
import com.sazib.pinboard.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class SplashInteractor @Inject constructor(
  preferenceHelper: PreferenceHelper,
  apiHelper: ApiHelper
) : BaseInteractor(preferenceHelper, apiHelper), SplashMVPInteractor