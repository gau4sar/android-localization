package com.example.android_localization.screens.home.screen_items


sealed class MainScreenItems(val route:String) {

    object HomeScreenItems : MainScreenItems("HomeScreenItems")
}