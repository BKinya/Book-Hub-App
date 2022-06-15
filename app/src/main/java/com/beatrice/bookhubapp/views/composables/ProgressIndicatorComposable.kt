package com.beatrice.bookhubapp.views.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import logcat.logcat

@Composable
fun ProgressIndicatorComposable() {
  LinearProgressIndicator(
    modifier = Modifier.fillMaxWidth()
  )
}

@Composable
fun ErrorMessageComposable(errorMessage: String) {
  Text(
    text = errorMessage, modifier = Modifier.padding(top = 18.dp, bottom = 18.dp).fillMaxWidth(),
    fontSize = 22.sp,
    textAlign = TextAlign.Center,
    fontFamily = FontFamily.Serif,
    color = Color.Magenta,
  )
}