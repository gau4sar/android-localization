package com.example.android_localization.screens.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_localization.di.appModule
import com.example.android_localization.di.viewModelModule
import com.example.android_localization.screens.home.screen_items.MainScreenItems
import com.example.android_localization.ui.theme.AndroidlocalizationTheme
import com.example.android_localization.utils.AppLanguage
import com.example.android_localization.utils.customAnimatedComposable
import com.example.android_localization.viewmodel.SharedViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNav
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModel()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var language = ""
        var countryCode = ""

        getLanguage(sharedViewModel.prefUtils.getAppLanguage()){lang, country ->
            language = lang
            countryCode = country
        }

        val locale = Locale(language, countryCode)

        Locale.setDefault(locale)
        val config = Configuration()
        config.setToDefaults()

        config.setLocale(locale)
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )

        setContent {
            AndroidlocalizationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberAnimatedNavController()

                    Koin(appDeclaration = {
                        modules(
                            appModule,
                            viewModelModule
                        )
                    }) {

                        KoinNav(navController) {
                            AnimatedNavHost(
                                navController,
                                startDestination =
                                MainScreenItems.HomeScreenItems.route
                            ) {

                                customAnimatedComposable(route = MainScreenItems.HomeScreenItems.route,
                                    content = {
                                        HomeScreen()
                                    })
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getLanguage(
        appLanguage: AppLanguage,
        emit: (lang: String, country: String) -> Unit
    ) {
        var lang = ""
        var country = ""
        when (appLanguage) {
            AppLanguage.ENGLISH -> {
                lang = "en"
                country = "UK"
            }
            AppLanguage.SPANISH -> {
                lang = "es"
                country = "ES"
            }
            AppLanguage.ARABIC -> {
                lang = "ar"
                country = "AE"
            }
        }

        emit(lang, country)
    }
}
