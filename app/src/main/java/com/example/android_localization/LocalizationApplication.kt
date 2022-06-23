package com.example.android_localization

import android.app.Application
import com.example.android_localization.di.appModule
import com.example.android_localization.di.repositoryModule
import com.example.android_localization.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class LocalizationApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@LocalizationApplication)
            modules(
                listOf(
                    appModule,
                    viewModelModule,
                    repositoryModule
                )
            )
        }
    }
}