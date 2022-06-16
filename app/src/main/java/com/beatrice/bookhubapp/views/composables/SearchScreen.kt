package com.beatrice.bookhubapp.views.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.beatrice.bookhubapp.R
import com.beatrice.bookhubapp.viewmodels.BookViewModel
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun SearchScreen(
  bookViewModel: BookViewModel = viewModel(),
) {
  val inputState = rememberSearchInputState(initialText = "")
  val lifecycleOwner = LocalLifecycleOwner.current
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .padding(16.dp)
  ) {
    Text(
      text = stringResource(R.string.enter_search_term_label),
      modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
      fontSize = 20.sp,
      textAlign = TextAlign.Start,
      fontFamily = FontFamily.Serif
    )

    SearchInputComposable(
      inputSate = inputState,
      onButtonClicked = {
        bookViewModel.getBooks(inputState.searchTerm)
      })

    BooksStateComposable(lifecycleOwner = lifecycleOwner)
  }
}




