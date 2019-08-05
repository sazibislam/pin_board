package com.sazib.pinboard.ui.pinboard

import androidx.recyclerview.widget.GridLayoutManager
import com.sazib.pinboard.ui.pinboard.interactor.PinboardInteractor
import com.sazib.pinboard.ui.pinboard.interactor.PinboardMVPInteractor
import com.sazib.pinboard.ui.pinboard.presenter.PinboardMVPPresenter
import com.sazib.pinboard.ui.pinboard.presenter.PinboardPresenter
import com.sazib.pinboard.ui.pinboard.view.PinboardActivity
import com.sazib.pinboard.ui.pinboard.view.PinboardMVPView
import com.sazib.pinboard.ui.pinboard.view.adapter.PinboardAdapter
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

  @Provides internal fun providePinboardGridLayoutManager(activity: PinboardActivity):
      GridLayoutManager = GridLayoutManager(activity, 2)

  @Provides internal fun providePinboardAdapter(): PinboardAdapter = PinboardAdapter()

}