package com.example.android_localization.screens

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
import com.example.android_localization.di.repositoryModule
import com.example.android_localization.di.viewModelModule
import com.example.android_localization.screens.home.HomeScreen
import com.example.android_localization.screens.screen_items.MainScreenItems
import com.example.android_localization.ui.theme.AndroidlocalizationTheme
import com.example.android_localization.utils.customAnimatedComposable
import com.example.android_localization.viewmodel.HomeViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNav
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

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
                            viewModelModule,
                            repositoryModule,
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
                                        HomeScreen(homeViewModel)
                                    })
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getSavedLanguage()
    }
}
