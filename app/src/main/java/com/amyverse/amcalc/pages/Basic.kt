package com.amyverse.amcalc.pages

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amyverse.amcalc.ui.theme.poppins_family

@Composable
fun BasicPage() {
    val gray100 = Color(0xffe5e5e5)
    val gray200 = Color(0xffd0d0d0)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gray100)
    ) {
        Column(modifier = Modifier.statusBarsPadding()) {
            Text(
                text = "Hii There Im under the Status Bar", style = TextStyle(
                    fontSize = 55.sp, fontFamily = poppins_family, fontWeight = FontWeight.Medium
                )
            )
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .background(Color.Blue)
                    .size(55.dp)
                    .align(Alignment.CenterHorizontally)
            ) {

            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .size(55.dp)
                    .align(Alignment.CenterHorizontally)
            ) {

            }
            Spacer(modifier = Modifier.size(150.dp))
            Box(
                modifier = Modifier
                    .background(gray100)
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally), contentAlignment = Alignment.Center
            ) {
                var pressed = false
                val offsize by remember { mutableStateOf(10) }
                val blursize by remember { mutableStateOf(10.dp) }
                val offset by animateIntAsState(
                    targetValue = if (pressed) 2 else offsize,
                    label = "",
                    animationSpec = tween(durationMillis = 100, easing = LinearOutSlowInEasing),
                )
                val blur by animateDpAsState(
                    targetValue = if (pressed) 4.dp else blursize,
                    label = "",
                    animationSpec = tween(durationMillis = 100, easing = LinearOutSlowInEasing),
                )
                val interactionSource by remember {
                    mutableStateOf(MutableInteractionSource())
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset(-offset, -offset) }
                    .blur(blur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    .background(Color.White, CircleShape))
                Box(modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset(offset, offset) }
                    .blur(blur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    .background(gray200, CircleShape))
                Box(
                    modifier = Modifier
                        .clickable(
                            onClick = { pressed = true },
                            indication = null,
                            interactionSource = interactionSource
                        )
                        .fillMaxSize()
                        .background(/*brush = Brush.verticalGradient(
                                colors = listOf(
                                    gray100,
                                    gray200,
                                )
                            ),*/
                            gray100, shape = CircleShape
                        )/*.border(
                        width = 1.dp,
                        shape = CircleShape,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White,
                                Color.Black.copy(alpha = .15f)
                            )
                        )
                    )*/
                ) {

                }
            }
        }
    }
}

@Preview
@Composable
fun Show(modifier: Modifier = Modifier) {
    BasicPage()
}