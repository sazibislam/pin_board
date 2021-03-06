package com.sazib.pinboard.di.module

import android.app.Application
import android.content.Context
import com.sazib.pinboard.BuildConfig
import com.sazib.pinboard.data.network.ApiHeader
import com.sazib.pinboard.data.network.ApiHelper
import com.sazib.pinboard.data.network.AppApiHelper
import com.sazib.pinboard.data.preferences.AppPreferenceHelper
import com.sazib.pinboard.data.preferences.PreferenceHelper
import com.sazib.pinboard.di.AppKey
import com.sazib.pinboard.di.PackageName
import com.sazib.pinboard.di.PreferenceInfo
import com.sazib.pinboard.di.VersionName
import com.sazib.pinboard.utils.AppConstants
import com.sazib.pinboard.utils.CertSHA1
import com.sazib.pinboard.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

  @Provides @Singleton internal fun provideContext(application: Application): Context = application

  @Provides @AppKey internal fun provideAppKey(context: Context): String =
    CertSHA1.getCertificateSHA1(context.applicationContext)

  @Provides @PackageName internal fun providePackageName(): String = BuildConfig.APPLICATION_ID

  @Provides @VersionName internal fun provideVersionName(): String = BuildConfig.VERSION_NAME

  @Provides @PreferenceInfo internal fun providePrefFileName(): String = AppConstants.PREF_NAME

  @Provides @Singleton internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper):
      PreferenceHelper = appPreferenceHelper

  @Provides @Singleton internal fun provideProtectedApiHeader(
    @AppKey app_key: String,
    @PackageName packageName: String,
    @VersionName versionName: String
  ): ApiHeader.AuthApiHeader = ApiHeader.AuthApiHeader(
      _app_key = app_key,
      _package = packageName,
      _version = versionName
  )

  @Provides @Singleton internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper =
    appApiHelper

  @Provides internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

  @Provides internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

}