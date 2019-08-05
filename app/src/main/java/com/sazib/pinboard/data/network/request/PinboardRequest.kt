package com.sazib.pinboard.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PinboardRequest(
  @Expose @SerializedName("token") internal val token: String? = ""
)
