package com.beatrice.bookhubapp.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beatrice.bookhubapp.R
import logcat.logcat

@Composable
fun SearchComposable(
  searchTerm: String,
  onValueChanged: (String) -> Unit,
  onButtonClicked: () -> Unit
) {
  Row(modifier = Modifier.padding(
    top = 8.dp,
    bottom = 16.dp
  )) {
    OutlinedTextField(
      value = searchTerm,
      onValueChange = onValueChanged,
      modifier = Modifier.padding(end = 8.dp),
      shape = RoundedCornerShape(16.dp)
    )
    Button(
      onClick = onButtonClicked,
      modifier = Modifier.size(width = 64.dp, height = 50.dp),
      shape = RoundedCornerShape(16.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.ic_outline_search_24),
        contentDescription = stringResource(R.string.search_icon)
      )
    }
  }
}