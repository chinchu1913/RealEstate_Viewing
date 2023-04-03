package com.example.realestate.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realestate.R
import com.example.realestate.ui.theme.ColorStatusConnected
import com.example.realestate.ui.theme.ColorStatusNotConnected
import com.example.realestate.ui.theme.MainFontColor
import com.example.realestate.ui.theme.SubFontColor

@Composable
fun DetailsRowItem(tittle: String, value: String, subTitle: String, subValue: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = tittle,
                color = MainFontColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )

            Text(
                text = if (value != "0") value else "-",
                color = SubFontColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = subTitle,
                color = MainFontColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
            Text(
                text = if (subValue != "0") subValue else "-",
                color = SubFontColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }

    }
}


@Composable
fun NotConnectedComponent() {
    Box(
        modifier = Modifier
            .background(color = ColorStatusNotConnected)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.text_no_connectivity
            ),
            color = White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


@Composable
fun ConnectedComponent() {
    Box(
        modifier = Modifier
            .background(color = ColorStatusConnected)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.text_connectivity
            ),
            color = White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun ErrorComponent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            stringResource(id = R.string.text_error_text),
            style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
            textAlign = TextAlign.Center
        )
    }
}