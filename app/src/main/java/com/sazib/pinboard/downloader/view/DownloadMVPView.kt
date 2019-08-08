package com.sazib.pinboard.downloader.view

import com.sazib.pinboard.downloader.DirPath
import com.sazib.pinboard.downloader.FileName
import com.sazib.pinboard.ui.base.view.MVPView

interface DownloadMVPView : MVPView {

  fun initView()

  //fun setupData()

  fun openFile(fileName: String)

  fun getDir(): Pair<DirPath, FileName>
}