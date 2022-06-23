package com.example.android_localization.data

data class LocalizationBundle(
    val en : Localization = Localization(),
    val cn : Localization = Localization(),
    val mm : Localization = Localization()
)