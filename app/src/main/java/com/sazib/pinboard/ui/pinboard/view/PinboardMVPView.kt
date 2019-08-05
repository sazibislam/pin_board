package com.sazib.pinboard.ui.pinboard.view

import com.sazib.pinboard.ui.base.view.MVPView
import com.sazib.pinboard.ui.pinboard.view.model.DataModel

interface PinboardMVPView : MVPView {

  fun initView()

  fun setupData(data: List<DataModel>)

}