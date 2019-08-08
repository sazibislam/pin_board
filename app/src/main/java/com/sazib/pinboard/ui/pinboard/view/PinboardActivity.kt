package com.sazib.pinboard.ui.pinboard.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.sazib.pinboard.R
import com.sazib.pinboard.data.network.response.PinboardResponse
import com.sazib.pinboard.downloader.DirPath
import com.sazib.pinboard.downloader.FileName
import com.sazib.pinboard.downloader.FileUtils
import com.sazib.pinboard.ui.base.view.DaggerActivity
import com.sazib.pinboard.ui.pinboard.interactor.PinboardMVPInteractor
import com.sazib.pinboard.ui.pinboard.presenter.PinboardMVPPresenter
import com.sazib.pinboard.ui.pinboard.view.adapter.PinboardAdapter
import kotlinx.android.synthetic.main.activity_pinboard.faBtn
import kotlinx.android.synthetic.main.activity_pinboard.listPinboard
import kotlinx.android.synthetic.main.activity_pinboard.nestedScrollView
import kotlinx.android.synthetic.main.activity_pinboard.toolbarPinboard
import javax.inject.Inject

class PinboardActivity : DaggerActivity(), PinboardMVPView, PinboardAdapter.Callback {

  @Inject lateinit var presenter: PinboardMVPPresenter<PinboardMVPView, PinboardMVPInteractor>
  @Inject lateinit var layoutManager: GridLayoutManager
  @Inject lateinit var adapter: PinboardAdapter

  companion object {
    fun getStartIntent(context: Context): Intent =
      Intent(context, PinboardActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
    setContentView(R.layout.activity_pinboard)
    presenter.onAttach(this)
  }

  /**
   * Instantiation of adapter, layoutManager, itemAnimator and Floating button
   * **/
  override fun initView() {

    setupToolbar(toolbarPinboard, "Pinboard")

    listPinboard.apply {
      layoutManager = this@PinboardActivity.layoutManager
      itemAnimator = DefaultItemAnimator()
      adapter = this@PinboardActivity.adapter.apply {
        setCallback(this@PinboardActivity)
      }
    }
    listPinboard.setHasFixedSize(true)

    /**
     * when someone scroll view then Floating button visible
     * And another will happen request and image data will be added to adapter
     * **/
    nestedScrollView.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
      if (scrollY > 0) {
        faBtn.visibility = View.VISIBLE
        faBtn.show()
        //presenter.getData(initRequest = false)
      } else {
        faBtn.visibility = View.INVISIBLE
        faBtn.hide()
      }
    }

    /**
     * Floating button on click listner perform
     * **/
    faBtn.setOnClickListener {
      nestedScrollView.fullScroll(View.FOCUS_UP)
    }
  }

  /**
   * Destroying the presenter
   * **/
  override fun onDestroy() {
    presenter.onDetach()
    super.onDestroy()
  }

  /**
   * Before initial request data added to adapter
   * **/
  override fun setupData(data: List<PinboardResponse>) = adapter.addDataToList(data)

  /**
   * After initial request data added to adapter
   * **/
  override fun _setupData(data: List<PinboardResponse>) = adapter._addDataToList(data)

  /**
   * Get the directory and file name
   * **/
  override fun getDefultDir(): Pair<DirPath, FileName> = FileUtils.getDerectoryInfo(this)

  /**
   * Perform Click Callback from adapter
   * **/
  override fun onClick(data: PinboardResponse) {

  }
}
