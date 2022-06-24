package com.example.android_localization.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android_localization.utils.AppLanguage
import com.example.android_localization.utils.PrefUtils

class SharedViewModel(
    val prefUtils: PrefUtils
) : ViewModel() {

    //Switch languages
    fun switchToEnglish() = switchLanguage(AppLanguage.ENGLISH)
    fun switchToSpanish() = switchLanguage(AppLanguage.SPANISH)
    fun switchToArabic() = switchLanguage(AppLanguage.ARABIC)

    private fun switchLanguage(language: AppLanguage) {
        prefUtils.saveAppLanguage(language)
    }

}