package com.example.android_localization.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_localization.data.Localization
import com.example.android_localization.repo.LocalizationRepository
import com.example.android_localization.utils.AppLanguage
import com.example.android_localization.utils.PrefUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val localizationRepository: LocalizationRepository,
    val prefUtils: PrefUtils
) : ViewModel() {


    //This is used in the screens
    val localizationFlow: StateFlow<Localization>
        get() = localizationRepository.localizationFlow


    //Current selected language
    private val _currentLanguage: MutableStateFlow<AppLanguage> =
        MutableStateFlow(prefUtils.getAppLanguage())
    val currentLanguageFlow: Flow<AppLanguage> = _currentLanguage


    //Switch languages
    fun switchToEnglish() = switchLanguage(AppLanguage.ENGLISH)
    fun switchToChinese() = switchLanguage(AppLanguage.CHINESE)
    fun switchToBurmese() = switchLanguage(AppLanguage.BURMESE)

    private fun switchLanguage(language: AppLanguage) {
        viewModelScope.launch {
            localizationRepository.switchLanguage(language)
            _currentLanguage.emit(language)
        }
    }

}