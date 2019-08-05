package com.sazib.pinboard.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PinboardResponse internal constructor(
  @SerializedName("DegreeList") @Expose var degreeList: ArrayList<DegreeList>? = null
) {
  data class DegreeList internal constructor(
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("degree_name") @Expose var degreeName: String? = null,
    @SerializedName("description") @Expose var description: String? = null,
    @SerializedName("status") @Expose var status: Int? = null,
    @SerializedName("created_by") @Expose var createdBy: Int? = null,
    @SerializedName("updated_by") @Expose var updatedBy: Int? = null,
    @SerializedName("deleted_at") @Expose var deletedAt: String? = null,
    @SerializedName("created_at") @Expose var createdAt: String? = null,
    @SerializedName("updated_at") @Expose var updatedAt: String? = null
  )
}
