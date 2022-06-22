package com.example.android_localization

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_localization.ui.theme.AndroidlocalizationTheme
import com.lokalise.sdk.LokaliseContextWrapper


class MainActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LokaliseContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidlocalizationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {

    Column(modifier = Modifier.fillMaxWidth()) {
        CustomMediumTextStyle(text = stringResource(R.string.watching_my_cats))
        CustomMediumTextStyle(text = stringResource(R.string.in_ancient_times_cats))
        CustomMediumTextStyle(text = stringResource(R.string.anyone_around_cat))
        CustomMediumTextStyle(text = stringResource(R.string.one_cat_another_cat))


        CustomMediumTextStyle(text = stringResource(R.string.owners_of_dogs_noticed))
        CustomMediumTextStyle(text = stringResource(R.string.i_like_dogs))
        CustomMediumTextStyle(text = stringResource(R.string.dog_loves_you))
        CustomMediumTextStyle(text = stringResource(R.string.starving_dog))
    }
}

@Composable
fun CustomMediumTextStyle(text:String){

    Column(modifier = Modifier.fillMaxWidth()) {
    Text(text = text, fontSize = 20.sp, fontFamily = FontFamily.SansSerif)

    Spacer(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 8.dp)
        .height(1.dp)
        .background(Color.Black))
    }
}