package com.sazib.pinboard.ui.pinboard.view

import com.sazib.pinboard.data.network.response.PinboardResponse
import com.sazib.pinboard.ui.base.view.MVPView
import com.sazib.pinboard.utils.downloader.DirPath
import com.sazib.pinboard.utils.downloader.FileName

interface PinboardMVPView : MVPView {

  fun initView()

  fun setupData(data: List<PinboardResponse>)

  fun getDefultDir(): Pair<DirPath, FileName>

}