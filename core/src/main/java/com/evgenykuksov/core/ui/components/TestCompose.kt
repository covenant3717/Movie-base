package com.evgenykuksov.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
private fun TestScreen() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyText(
            modifier = Modifier
                /*.align(End)*/
                .background(Color.Magenta),
            "Small text"
        )
//        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.weight(1f))
        MyText(
            modifier = Modifier
                /*.align(Start)*/
                .background(Color.Yellow),
            "Example"
        )
    }
}

@Composable
private fun TestScreen2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Center
    ) {
        Box(
            Modifier
                .width(160.dp)
                .height(200.dp)
//                .background(Color.Gray, RoundedCornerShape(16.dp))
//                .background(Color.Gray, CircleShape)
                .background(
                    brush = Brush.linearGradient(colors = listOf(Color.Yellow, Color.Black)),
                    shape = CutCornerShape(16.dp),
                    alpha = 0.7f
                )
                .border(
                    width = 4.dp,
                    shape = CutCornerShape(16.dp),
                    brush = Brush.linearGradient(colors = listOf(Color.Red, Color.Yellow, Color.Green))
                )
        )
    }
}

@Composable
private fun TestScreen3() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Center
    ) {
        Icon(
            painter = painterResource(com.evgenykuksov.core.R.drawable.ic_core_popcorn),
            tint = Color.DarkGray,
            contentDescription = null
        )
    }
}

@Composable
private fun MyText(modifier: Modifier, text: String) {
    Text(modifier = modifier, text = text, color = Color.Black, fontSize = 32.sp)
}

@Preview
@Composable
private fun TestScreenPreview() {
//    TestScreen()
//    TestScreen2()
    TestScreen3()
}