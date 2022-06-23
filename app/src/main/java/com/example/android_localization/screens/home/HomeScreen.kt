package com.example.android_localization.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_localization.data.Localization
import com.example.android_localization.utils.fullPageProgressBar
import com.example.android_localization.viewmodel.HomeViewModel


@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    LaunchedEffect(key1 = Unit, block = {
        homeViewModel.switchToEnglish()
    })

    val localization: State<Localization> = homeViewModel.localizationFlow.collectAsState(
        initial = Localization()
    )

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            CustomMediumTextStyle(text = localization.value.hello_world)
            CustomMediumTextStyle(text = localization.value.watching_my_cats)
            CustomMediumTextStyle(text = localization.value.in_ancient_times_cats)

            Spacer(modifier = Modifier.padding(26.dp))

            CustomMediumTextStyle(text = localization.value.i_like_dogs)
            CustomMediumTextStyle(text = localization.value.owners_of_dogs_noticed)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(onClick = { homeViewModel.switchToEnglish() }) {
                    Text(text = "English", fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                }

                Button(onClick = { homeViewModel.switchToSpanish() }) {
                    Text(text = "Spanish", fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                }

                Button(onClick = { homeViewModel.switchToArabic() }) {
                    Text(text = "Arabic", fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                }
            }

        }
    }

    if (homeViewModel.isSwitchLanguageInProgress.value) {
        fullPageProgressBar()
    }
}

@Composable
fun CustomMediumTextStyle(text: String) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            style = TextStyle(textDirection = TextDirection.Content)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
                .height(1.dp)
                .background(Color.Black)
        )
    }
}