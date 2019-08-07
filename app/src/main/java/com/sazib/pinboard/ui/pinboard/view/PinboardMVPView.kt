package com.sazib.pinboard.ui.pinboard.view

import com.sazib.pinboard.data.network.response.PinboardResponse
import com.sazib.pinboard.ui.base.view.MVPView

interface PinboardMVPView : MVPView {

  fun initView()

  fun setupData(data: List<PinboardResponse>)

}