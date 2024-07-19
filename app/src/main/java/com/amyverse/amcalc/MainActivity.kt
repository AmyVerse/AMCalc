package com.amyverse.amcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.amyverse.amcalc.ui.theme.AMCalcTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AMCalcTheme {
                //TransparentSystemBars()
                RootPage()
            }
        }
    }
}

/*
@Composable
fun TransparentSystemBars() {
    val window = (LocalContext.current as ComponentActivity).window
    val view = LocalView.current
    SideEffect {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.Transparent.toArgb()
        window.navigationBarColor = Color.White.toArgb()
        val controller = WindowInsetsControllerCompat(window, view)
        controller.isAppearanceLightStatusBars = true // For light icons and text
        controller.isAppearanceLightNavigationBars = true // For light icons and text
    }
}*/
