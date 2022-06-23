package com.example.android_localization.di

import com.example.android_localization.utils.PrefUtils
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { PrefUtils(androidContext()) }
}