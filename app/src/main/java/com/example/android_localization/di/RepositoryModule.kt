package com.example.android_localization.di

import com.example.android_localization.repo.LocalizationRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { LocalizationRepository(get()) }
}
