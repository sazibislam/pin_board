package com.sazib.pinboard.di.builder

import com.sazib.pinboard.ui.splash.SplashActivityModule
import com.sazib.pinboard.ui.splash.view.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
  abstract fun bindSplashActivity(): SplashActivity

}