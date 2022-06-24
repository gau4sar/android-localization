package com.example.android_localization.screens.switch_language.screen_items


sealed class SwitchScreenItems(val route:String) {

    object SwitchScreenItem : SwitchScreenItems("SwitchScreenItem")
}