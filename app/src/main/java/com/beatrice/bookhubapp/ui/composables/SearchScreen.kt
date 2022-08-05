package com.beatrice.bookhubapp.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.beatrice.bookhubapp.R
import com.beatrice.bookhubapp.util.UiState
import com.beatrice.bookhubapp.viewmodels.BookViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen() {
  val inputState = rememberSearchInputState(initialText = "")
  val lifecycleOwner = LocalLifecycleOwner.current
  val keyboardController = LocalSoftwareKeyboardController.current
  val bookViewModel: BookViewModel  = getViewModel()
  val booksFlow = bookViewModel.booksUiState
  val bookFlowLifeCycleAware = remember(booksFlow, lifecycleOwner) {
    booksFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
  }
  val booksUiState by bookFlowLifeCycleAware.collectAsState(UiState.Loading())// TODO: Check collectAsStateWithLifeCycle API
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
        keyboardController?.hide()
        bookViewModel.getBooks(inputState.searchTerm)
      })

    BooksStateComposable(booksUiState = booksUiState)
  }
}