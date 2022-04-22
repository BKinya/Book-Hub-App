package com.beatrice.bookhubapp

import android.app.Application
import com.beatrice.bookhubapp.di.dataLocalModules
import com.beatrice.bookhubapp.di.dataRemoteModules
import com.beatrice.bookhubapp.di.domainModules
import com.beatrice.bookhubapp.di.viewModelModules
import logcat.AndroidLogcatLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BookHubApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidLogger()
      androidContext(this@BookHubApplication)
      modules(
        viewModelModules,
        domainModules,
        dataRemoteModules,
        dataLocalModules
      )
    }

    AndroidLogcatLogger.installOnDebuggableApp(this)
  }
}