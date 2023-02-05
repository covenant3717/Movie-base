package com.evgenykuksov.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evgenykuksov.core.ui.theme.ThemeColors

@Composable
fun TopBar(
    @DrawableRes navigationIcon: Int,
    title: String = "",
    colorNavigationIcon: Color = ThemeColors.core_white100,
    colorText: Color = ThemeColors.core_white50,
    colorBackground: Color = ThemeColors.core_black100,
    onNavigationIconClick: () -> Unit = {}
) {
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text(text = title, color = colorText)
        },
        backgroundColor = colorBackground,
        navigationIcon = {
            IconButton(
                onClick = { onNavigationIconClick() }
            ) {
                Icon(
                    painter = painterResource(id = navigationIcon),
                    tint = colorNavigationIcon,
                    contentDescription = null
                )
            }
        },
        actions = {

        }
    )
}

@Preview("TopBar")
@Composable
private fun TopBarPreview() {
    TopBar(
        navigationIcon = com.evgenykuksov.core.R.drawable.ic_back,
        title = "My toolbar"
    )
}