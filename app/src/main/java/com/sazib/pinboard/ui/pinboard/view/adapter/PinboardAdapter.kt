package com.sazib.pinboard.ui.pinboard.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sazib.pinboard.R
import com.sazib.pinboard.ui.base.view.BaseViewHolder
import com.sazib.pinboard.ui.pinboard.view.model.DataModel
import kotlinx.android.synthetic.main.list_item_pinboard.view.ivPinboard
import kotlinx.android.synthetic.main.list_item_pinboard.view.tvTitle

class PinboardAdapter(private var data: MutableList<DataModel> = ArrayList()) :
    RecyclerView.Adapter<BaseViewHolder>() {

  private var callback: Callback? = null

  override fun getItemCount() = data.size

  override fun onBindViewHolder(
    holder: BaseViewHolder,
    position: Int
  ) = holder.onBind(position)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BaseViewHolder = ViewHolder(
      LayoutInflater.from(parent.context).inflate(
          R.layout.list_item_pinboard, parent, false
      )
  )

  internal fun addDataToList(data: List<DataModel>) {
    this.data.clear()
    this.data.addAll(data)
    notifyDataSetChanged()
  }

  internal fun setCallback(callback: Callback) {
    this.callback = callback
  }

  inner class ViewHolder(view: View) : BaseViewHolder(view) {

    override fun clear() {
      itemView.tvTitle.text = ""
      itemView.ivPinboard.setImageDrawable(null)

    }

    override fun onBind(position: Int) {
      val model = data[position]

      model.title?.let { title_ -> itemView.tvTitle.text = title_ }
      model.image?.let { image_ -> itemView.ivPinboard.setImageResource(image_) }

      itemView.setOnClickListener { callback?.onClick(model) }
    }
  }

  interface Callback {
    fun onClick(data: DataModel)
  }
}