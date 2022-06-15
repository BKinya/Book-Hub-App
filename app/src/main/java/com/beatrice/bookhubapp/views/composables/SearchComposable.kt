package com.beatrice.bookhubapp.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beatrice.bookhubapp.R
import logcat.logcat

@Composable
fun SearchComposable(
  searchTerm: MutableState<String> = rememberSaveable { mutableStateOf("")},
  onValueChanged: (String) -> Unit = { newString -> searchTerm.value =  newString},
  onClear: () -> Unit = { searchTerm.value = ""},
  onButtonClicked: () -> Unit
) {

  val clearButton = @Composable {
    IconButton(
      onClick = onClear,
    ) {
      Icon(
        Icons.Default.Clear,
        contentDescription = "Clear button",
        tint = Color.Black
      )
    }
  }
  val showClearButton by remember {
    derivedStateOf {
      logcat("SearchLogs"){"Checking.."}
      searchTerm.value.isNotBlank() || searchTerm.value.isNotEmpty() }
  }
  Row(modifier = Modifier.padding(
    top = 8.dp,
    bottom = 16.dp
  )) {
    OutlinedTextField(
      value = searchTerm.value,
      onValueChange = onValueChanged,
      modifier = Modifier.padding(end = 8.dp),
      shape = RoundedCornerShape(16.dp),
      trailingIcon = if(showClearButton) clearButton else null
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