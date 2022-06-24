package com.example.android_localization.screens.switch_language

import android.content.Intent
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
import com.example.android_localization.screens.home.MainActivity
import com.example.android_localization.screens.home.screen_items.MainScreenItems
import com.example.android_localization.ui.theme.AndroidlocalizationTheme
import com.example.android_localization.utils.customAnimatedComposable
import com.example.android_localization.viewmodel.SharedViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNav
import org.koin.androidx.viewmodel.ext.android.viewModel


class SwitchLanguageActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModel()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                                        SwitchLanguageScreen(sharedViewModel)
                                    })
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

        super.onBackPressed()
    }
}