package com.sazib.pinboard.ui.pinboard

import com.sazib.pinboard.ui.pinboard.interactor.PinboardInteractor
import com.sazib.pinboard.ui.pinboard.interactor.PinboardMVPInteractor
import com.sazib.pinboard.ui.pinboard.presenter.PinboardMVPPresenter
import com.sazib.pinboard.ui.pinboard.presenter.PinboardPresenter
import com.sazib.pinboard.ui.pinboard.view.PinboardMVPView
import dagger.Module
import dagger.Provides

@Module
class PinboardActivityModule {

  @Provides
  internal fun providePinboardInteractor(
    interactor: PinboardInteractor
  ): PinboardMVPInteractor = interactor

  @Provides
  internal fun providePinboardPresenter(
    presenter: PinboardPresenter<PinboardMVPView, PinboardMVPInteractor>
  ): PinboardMVPPresenter<PinboardMVPView, PinboardMVPInteractor> = presenter

}