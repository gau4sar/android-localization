package com.example.android_localization.screens.switch_language

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_localization.screens.home.MainActivity
import com.example.android_localization.utils.AppLanguage
import com.example.android_localization.viewmodel.SharedViewModel
import timber.log.Timber


@SuppressLint("UnrememberedMutableState")
@Composable
fun SwitchLanguageScreen(sharedViewModel: SharedViewModel) {

    val context = LocalContext.current
    var currentSelectedLanguage by remember {
        mutableStateOf("")
    }
    var selectedButtonLanguage by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = Unit, block = {
        currentSelectedLanguage = sharedViewModel.prefUtils.getAppLanguage().value
        selectedButtonLanguage = currentSelectedLanguage

        Timber.d("selectedButtonLanguage--> $selectedButtonLanguage")
    })

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    onClick = {
                        sharedViewModel.switchToEnglish()
                        selectedButtonLanguage = AppLanguage.ENGLISH.value
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = getBackgroundColor(
                            selectedButtonLanguage == AppLanguage.ENGLISH.value
                        )
                    )
                ) {
                    Text(
                        text = "English", fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = getTextColor(
                            selectedButtonLanguage == AppLanguage.ENGLISH.value
                        )
                    )
                }

                Button(
                    onClick = {
                        sharedViewModel.switchToSpanish()
                        selectedButtonLanguage = AppLanguage.SPANISH.value
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = getBackgroundColor(
                            selectedButtonLanguage == AppLanguage.SPANISH.value
                        )
                    )
                ) {
                    Text(
                        text = "Spanish",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = getTextColor(
                            selectedButtonLanguage == AppLanguage.SPANISH.value
                        )
                    )
                }

                Button(
                    onClick = {
                        sharedViewModel.switchToArabic()
                        selectedButtonLanguage = AppLanguage.ARABIC.value
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = getBackgroundColor(
                            selectedButtonLanguage == AppLanguage.ARABIC.value
                        )
                    )
                ) {
                    Text(
                        text = "Arabic", fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = getTextColor(
                            selectedButtonLanguage == AppLanguage.ARABIC.value
                        )
                    )
                }



                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as SwitchLanguageActivity).finish()
                    }
                ) {
                    Text(
                        text = "Done", fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }

        }
    }
}

fun getBackgroundColor(isSelected: Boolean): Color {
    return if (isSelected) {
        Color.Blue
    } else {
        Color.White
    }
}

fun getTextColor(isSelected: Boolean): Color {
    return if (isSelected) {
        Color.White
    } else {
        Color.Black
    }
}