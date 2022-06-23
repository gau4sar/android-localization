package com.example.android_localization.utils

import android.content.Context


const val LOCALIZATION_PREFERENCES = "LOCALIZATION_PREFERENCES"
const val SAVED_LANG = "SAVED_LANG"

class PrefUtils(context: Context) {

    private val preferences =
        context.getSharedPreferences(LOCALIZATION_PREFERENCES, Context.MODE_PRIVATE)


    fun getAppLanguage(): AppLanguage {
        return try {
            val value = preferences.getString(SAVED_LANG, "ENGLISH").orEmpty()
            AppLanguage.valueOf(value)
        } catch(e: Exception) {
            AppLanguage.ENGLISH
        }
    }

    fun saveAppLanguage(language: AppLanguage) {
        preferences
            .edit()
            .putString(SAVED_LANG, language.name)
            .apply()
    }
}