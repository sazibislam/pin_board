package com.sazib.pinboard.di.component

import android.app.Application
import com.sazib.pinboard.di.builder.ActivityBuilder
import com.sazib.pinboard.di.builder.ServiceBuilder
import com.sazib.pinboard.di.module.AppModule
import com.sazib.pinboard.service.IVApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class), (ServiceBuilder::class)]
)
interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance fun application(application: Application): Builder
    fun build(): AppComponent
  }

  fun inject(app: IVApp)
}