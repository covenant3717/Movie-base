package com.evgenykuksov.core.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*override*/ fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
    ComposeView(inflater.context).apply {
        setContent {
            HomeScreen()
        }
    }

@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier.background(Color.Gray)
    ) {
        MyClickCounter()
        MyCheckBox()
        MyOutlinedTextField()
    }
}

@Composable
private fun MyClickCounter() {
    var couneter by remember { mutableStateOf(0) }
    Text(
        text = "Clicks: $couneter",
        fontSize = 40.sp,
        color = Color.White,
        modifier = Modifier
            .wrapContentHeight()
            .background(Color(0xFF4CAF50))
            .clickable { couneter++ }
            .padding(top = 80.dp, start = 20.dp)
    )
}

@Composable
private fun MyCheckBox() {
    val isChecked = remember { mutableStateOf(false) }
    Checkbox(
        modifier = Modifier
            .padding(top = 80.dp, start = 20.dp),
        checked = isChecked.value,
        onCheckedChange = { isChecked.value = it }
    )
}

@Composable
private fun MyOutlinedTextField() {
    val textState = remember { mutableStateOf("Some text") }
    OutlinedTextField(
        modifier = Modifier
            .padding(top = 80.dp, start = 20.dp),
        value = textState.value,
        onValueChange = { textState.value = it }
    )
}
