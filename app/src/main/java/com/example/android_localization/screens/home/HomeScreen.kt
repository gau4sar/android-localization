package com.example.android_localization.screens.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_localization.R
import com.example.android_localization.screens.switch_language.SwitchLanguageActivity


@Composable
fun HomeScreen() {

    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            CustomMediumTextStyle(text = stringResource(id = R.string.hello_world))

            Spacer(modifier = Modifier.padding(26.dp))

            CustomMediumTextStyle(text = stringResource(id = R.string.in_ancient_times_cats))
            CustomMediumTextStyle(text = stringResource(id = R.string.anyone_around_cat))

            Spacer(modifier = Modifier.padding(26.dp))

            CustomMediumTextStyle(text = stringResource(id = R.string.dog_loves_you))
            CustomMediumTextStyle(text = stringResource(id = R.string.owners_of_dogs_noticed))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(onClick = {
                    val intent = Intent(context, SwitchLanguageActivity::class.java)
                    context.startActivity(intent)
                    (context as MainActivity).finish()
                }) {
                    Text(
                        text = "Switch Language",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }

        }
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