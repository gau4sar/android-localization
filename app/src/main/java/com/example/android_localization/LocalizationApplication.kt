package com.example.android_localization

import android.app.Application
import com.lokalise.sdk.Lokalise

class LocalizationApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Lokalise.init(
            this,
            getString(R.string.lokalise_sdk_token),
            getString(R.string.lokalise_project_id)
        );  // 1

        Lokalise.updateTranslations();  // 2
    }
}