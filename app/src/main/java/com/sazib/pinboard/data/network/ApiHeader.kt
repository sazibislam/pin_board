package com.sazib.pinboard.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sazib.pinboard.di.AppKey
import com.sazib.pinboard.di.PackageName
import com.sazib.pinboard.di.VersionName
import javax.inject.Inject

class ApiHeader @Inject constructor(internal val authApiHeader: AuthApiHeader) {

  data class AuthApiHeader @Inject constructor(
    @AppKey @Expose @SerializedName("app_key") val _app_key: String,
    @PackageName @Expose @SerializedName("package") val _package: String,
    @VersionName @Expose @SerializedName("version") val _version: String
  )
}