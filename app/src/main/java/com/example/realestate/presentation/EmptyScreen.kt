package com.example.library.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realestate.ui.theme.MainFontColor

/*
Composable for EmptyScreen
 */
@Composable
fun EmptyScreen(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            color = MainFontColor,
            maxLines = 1,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}