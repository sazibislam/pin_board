package com.sazib.pinboard.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PinboardResponse internal constructor(
  @SerializedName("id") @Expose var id: String? = null,
  @SerializedName("created_at") @Expose var created_at: String? = null,
  @SerializedName("width") @Expose var width: Double? = null,
  @SerializedName("height") @Expose var height: Double? = null,
  @SerializedName("color") @Expose var color: String? = null,
  @SerializedName("likes") @Expose var likes: Int? = null,
  @SerializedName("liked_by_user") @Expose var liked_by_user: Boolean? = null,
  @SerializedName("user") @Expose var userList: UserData? = null,
  @SerializedName(
      "current_user_collections"
  ) @Expose var categories: ArrayList<Categories>? = null,
  @SerializedName("urls") @Expose var urlsList: UrlsList? = null,
  @SerializedName("links") @Expose var linksList: LinksList? = null
) {
  data class UserData internal constructor(
    @SerializedName("id") @Expose var id: String? = null,
    @SerializedName("username") @Expose var username: String? = null,
    @SerializedName("name") @Expose var name: String? = null,
    @SerializedName("profile_image") @Expose var profile_image: ProfileImage? = null,
    @SerializedName("links") @Expose var links: ProfileLinks? = null
  )

  data class UrlsList internal constructor(
    @SerializedName("raw") @Expose var raw: String? = null,
    @SerializedName("full") @Expose var full: String? = null,
    @SerializedName("regular") @Expose var regular: String? = null,
    @SerializedName("small") @Expose var small: String? = null,
    @SerializedName("thumb") @Expose var thumb: String? = null
  )

  data class LinksList internal constructor(
    @SerializedName("self") @Expose var id: String? = null,
    @SerializedName("html") @Expose var html: String? = null,
    @SerializedName("download") @Expose var download: String? = null
  )

  data class ProfileImage internal constructor(
    @SerializedName("small") @Expose var small: String? = null,
    @SerializedName("medium") @Expose var medium: String? = null,
    @SerializedName("large") @Expose var large: String? = null
  )

  data class ProfileLinks internal constructor(
    @SerializedName("self") @Expose var self: String? = null,
    @SerializedName("html") @Expose var html: String? = null,
    @SerializedName("photos") @Expose var photos: String? = null,
    @SerializedName("likes") @Expose var likes: String? = null
  )

  data class Categories internal constructor(
    @SerializedName("id") @Expose var id: String? = null,
    @SerializedName("title") @Expose var title: String? = null,
    @SerializedName("photo_count") @Expose var photo_count: Int? = null,
    @SerializedName(
        "links"
    ) @Expose var currentUserCollectionsLinks: ArrayList<CollectionsLinks>? = null
  )

  data class CollectionsLinks internal constructor(
    @SerializedName("self") @Expose var self: String? = null,
    @SerializedName("photos") @Expose var photos: String? = null
  )
}



