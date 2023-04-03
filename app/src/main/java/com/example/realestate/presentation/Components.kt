package com.example.library.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realestate.R
import com.example.realestate.ui.theme.AppBg
import com.example.realestate.ui.theme.MainFontColor
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


/*
Custom composable for the app bar
 */
@Composable
fun AppBarComponent(
    navigator: DestinationsNavigator,
    title: String,
    showBack: Boolean = false
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = AppBg,
        elevation = 2.dp
    ) {
        if (showBack) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Rounded.ArrowBack,
                contentDescription = stringResource(id = R.string.text_arrow_back),
                tint= Color.Black,
                modifier = Modifier.clickable {
                    navigator.popBackStack()
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
        }

        Text(
            title, textAlign = if (showBack) TextAlign.Start else TextAlign.Center,
            fontSize = 16.sp,
            color = MainFontColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}