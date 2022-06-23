package com.example.android_localization.repo

import com.example.android_localization.data.Localization
import com.example.android_localization.data.LocalizationBundle
import com.example.android_localization.utils.AppLanguage
import com.example.android_localization.utils.PrefUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LocalizationRepository(private val preferenceStorage: PrefUtils) :
    LocalizationRepositoryInterface {

    //TODO the local can be the default language and the remote can be updated in api call
    private val local = LocalizationBundle(
        en = Localization().apply {
            watching_my_cats = "Wathcing my cats make me happy"
            in_ancient_times_cats = "In ancient times cat..."
            owners_of_dogs_noticed = "Owner of dogs will have noticed that..."
            i_like_dogs = "I like dogs"
        },
        mm = Localization().apply {
            watching_my_cats = "မင်္ဂလာပါ။ from Remote"
            in_ancient_times_cats = "ရွေးချယ်ထားသောဘာသာစကား From Remote : %@"
            owners_of_dogs_noticed = "အင်္ဂလိပ်"
            i_like_dogs = "တရုတ်"
        },
        cn = Localization().apply {
            watching_my_cats = "你好 你好 你好 英语"
            in_ancient_times_cats = "选择的语言选择的语言 选择的语言"
            owners_of_dogs_noticed = "英语 你好 你好 英语 你好 "
            i_like_dogs = "缅甸语 选择的语言"
        }
    )
    private val remote = local


    //Update this value and screen will be updated
    private val _localizationFlow: MutableStateFlow<Localization> by lazy {
        MutableStateFlow(
            local.getLocalization(currentAppLanguage)
        )
    }


    //This is used in the screens
    val localizationFlow: StateFlow<Localization> = _localizationFlow
    private var cachedLocalizationBundle: LocalizationBundle? = null

    private val currentAppLanguage: AppLanguage
        get() = preferenceStorage.getAppLanguage()

    init {
        //Todo Call the api
        CoroutineScope(Dispatchers.IO).launch {
            getLocalizationFromRemote()
        }
    }

    private fun getLocalizationFromRemote() {
        // Fetch localization data from remote here
        remote.let {
            cachedLocalizationBundle = it
            _localizationFlow.value = it.getLocalization(currentAppLanguage)
        }
    }

    //Update the language
    fun switchLanguage(language: AppLanguage) {
        _localizationFlow.value = getLocalization(language)
        preferenceStorage.saveAppLanguage(language)
    }

    private fun getLocalization(language: AppLanguage): Localization =
        cachedLocalizationBundle?.getLocalization(language)
            ?: local.getLocalization(language)


    //Get the selected language
    fun LocalizationBundle.getLocalization(language: AppLanguage): Localization = when (language) {
        AppLanguage.ENGLISH -> en
        AppLanguage.CHINESE -> cn
        AppLanguage.BURMESE -> mm
    }


}