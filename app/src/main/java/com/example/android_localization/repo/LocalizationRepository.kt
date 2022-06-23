package com.example.android_localization.repo

import com.example.android_localization.data.Localization
import com.example.android_localization.data.LocalizationBundle
import com.example.android_localization.utils.AppLanguage
import com.example.android_localization.utils.PrefUtils
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber


class LocalizationRepository(private val preferenceStorage: PrefUtils) :
    LocalizationRepositoryInterface {

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
    fun switchLanguage(language: AppLanguage, onFinishLoading: () -> Unit) {

        val db = Firebase.firestore

        db.collection(language.value)
            .get()
            .addOnSuccessListener { result ->
                Timber.d("firebase collection result -> $result")

                preferenceStorage.saveAppLanguage(language)

                val localization = Localization()
                for (document in result) {

                    document.get("hello_world").toString().let {
                        localization.hello_world = it
                    }
                    document.get("i_like_dogs").toString().let {
                        localization.i_like_dogs = it
                    }
                    document.get("watching_my_cats").toString().let {
                        localization.watching_my_cats = it
                    }
                    document.get("in_ancient_times_cats").toString().let {
                        localization.in_ancient_times_cats = it
                    }
                    document.get("owners_of_dogs_noticed").toString().let {
                        localization.owners_of_dogs_noticed = it
                    }
                    preferenceStorage.saveAppLanguage(language)

                    Timber.d("firebase collection-> ${document.id} => ${document.get("hello_world")}")
                }

                _localizationFlow.value = localization

                onFinishLoading()
            }
            .addOnFailureListener { exception ->
                Timber.e("firebase collection-> Error getting documents. $exception")
                onFinishLoading()
            }

        /*_localizationFlow.value = getLocalization(language)
        */
    }

    private fun getLocalization(language: AppLanguage): Localization =
        cachedLocalizationBundle?.getLocalization(language)
            ?: local.getLocalization(language)


    //Get the selected language
    fun LocalizationBundle.getLocalization(language: AppLanguage): Localization = when (language) {
        AppLanguage.ENGLISH -> en
        AppLanguage.SPANISH -> cn
        AppLanguage.ARABIC -> mm
    }


}