package com.beatrice.bookhubapp.views.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import logcat.logcat

@Composable
fun ProgressComposable(isLoading: Boolean) {
  logcat("ProgressComposable") { "When $isLoading" }
  if (isLoading) {
    LinearProgressIndicator(
      modifier = Modifier.fillMaxWidth()
    )
  }
}