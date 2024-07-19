package com.amyverse.amcalc

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amyverse.amcalc.pages.AdvancePage
import com.amyverse.amcalc.pages.BasicPage
import com.amyverse.amcalc.pages.SettingsPage
import com.amyverse.amcalc.ui.theme.poppins_family

@Composable
fun RootPage() {
    var currentIndex by remember { mutableIntStateOf(1) }
    val pages: List<@Composable () -> Unit> =
        listOf({ AdvancePage() }, { BasicPage() }, { SettingsPage() })


    Scaffold(modifier = Modifier
        .navigationBarsPadding()
        .background(Color.Blue), bottomBar = {
        BottomAppBar(
            modifier = Modifier.height(50.dp),
            containerColor = Color.White,
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                TabItem(modifier = Modifier.weight(1f),
                    title = "Advance",
                    isSelected = currentIndex == 0,
                    onTap = { currentIndex = 0 })

                TabItem(modifier = Modifier.weight(1f),
                    title = "Basic",
                    isSelected = currentIndex == 1,
                    onTap = { currentIndex = 1 })
                TabItem(modifier = Modifier.weight(1f),
                    title = "Settings",
                    isSelected = currentIndex == 2,
                    onTap = { currentIndex = 2 })
            }
        }
    }

    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateTopPadding())
        ) {
            pages[currentIndex]()
        }
    }
}

@Composable
fun TabItem(
    title: String, isSelected: Boolean, onTap: () -> Unit, modifier: Modifier = Modifier,
) {
    val dotSize by remember { mutableStateOf(0.dp) }
    val targetSize = if (isSelected) 5.dp else dotSize
    val size by animateDpAsState(
        targetValue = targetSize,
        animationSpec = tween(durationMillis = 275, easing = LinearOutSlowInEasing),
        label = "dot anime"
    )
    val interactionSource by remember {
        mutableStateOf(MutableInteractionSource())
    }

    Column(
        modifier = modifier
//            .background(Color.Red)
            .padding(bottom = 1.dp)
            .fillMaxHeight()
            .clickable(
                onClick = onTap, indication = null, interactionSource = interactionSource
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            color = if (isSelected) Color.Black else Color.Gray,
            fontFamily = poppins_family
        )

        Box(
            modifier = Modifier
                .padding(top = 1.dp)
                .size(size)
                .background(Color.Black, shape = CircleShape)
        )

    }
}