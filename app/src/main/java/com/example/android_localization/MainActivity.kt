package com.example.android_localization

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_localization.ui.theme.AndroidlocalizationTheme
import com.example.android_localization.utils.Constant.ENGLISH
import com.example.android_localization.utils.Constant.SPANISH
import java.util.*

var lang = "en"
var country = "UK"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locale = Locale(lang, country)

        Locale.setDefault(locale)
        val config = Configuration()
        config.setToDefaults()

        config.setLocale(locale)
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
        /*baseContext.createConfigurationContext(config)*/

/*        val config = resources.configuration
        val lang = "fa" // your language code
        val locale = Locale(lang)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            config.setLocale(locale)
        else
            config.locale = locale

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)*/

       /* val overrideConfiguration = baseContext.resources.configuration
        overrideConfiguration.setLocale(locale)*/
        /*val context: Context = createConfigurationContext(overrideConfiguration)
        val resources: Resources = context.resources*/


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

    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            CustomMediumTextStyle(text = stringResource(R.string.watching_my_cats))
            CustomMediumTextStyle(text = stringResource(R.string.in_ancient_times_cats))
            CustomMediumTextStyle(text = stringResource(R.string.anyone_around_cat))
            CustomMediumTextStyle(text = stringResource(R.string.one_cat_another_cat))


            CustomMediumTextStyle(text = stringResource(R.string.owners_of_dogs_noticed))
            CustomMediumTextStyle(text = stringResource(R.string.i_like_dogs))
            CustomMediumTextStyle(text = stringResource(R.string.dog_loves_you))
            CustomMediumTextStyle(text = stringResource(R.string.starving_dog))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

                Button(onClick = { setLanguage(ENGLISH, context) }) {
                    Text(text = "English", fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                }

                Button(onClick = { setLanguage(SPANISH, context) }) {
                    Text(text = "Spanish", fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                }
            }

        }
    }
}

fun setLanguage(s: String, context: Context) {

    when(s){
        ENGLISH -> {
            lang = "en"
            country = "UK"
        }
        SPANISH->{
            lang = "es"
            country = "ES"
        }
    }

    /*val locale = Locale(lang, country)

    Locale.setDefault(locale)

    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(
        config,
        context.resources.displayMetrics
    )*//*

    context.createConfigurationContext(config)*/

    (context as MainActivity).recreate()
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