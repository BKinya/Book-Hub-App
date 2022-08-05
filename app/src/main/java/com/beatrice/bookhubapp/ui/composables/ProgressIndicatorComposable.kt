package com.beatrice.bookhubapp.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import logcat.logcat

const val PROGRESS_INDICATOR_TAG = "progressIndicatorTag"
const val ERROR_UI_TAG = "errorUiTag"
@Composable
fun ProgressIndicatorComposable(modifier: Modifier = Modifier) {
  LinearProgressIndicator(
    modifier = modifier.fillMaxWidth().testTag(PROGRESS_INDICATOR_TAG)
  )
}


@Composable
fun ErrorMessageComposable(errorMessage: String, modifier: Modifier = Modifier) {
  Text(
    text = errorMessage,
    modifier = modifier.padding(top = 18.dp, bottom = 18.dp).fillMaxWidth().testTag(ERROR_UI_TAG),
    fontSize = 22.sp,
    textAlign = TextAlign.Center,
    fontFamily = FontFamily.Serif,
    color = Color.Magenta,
  )
}