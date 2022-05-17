package com.beatrice.bookhubapp.views.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.beatrice.dataremote.api.createOkClient
import com.beatrice.domain.models.Book
import logcat.logcat

@Composable
fun SearchScreen(
  bookViewModel: BookViewModel = viewModel(),
) {
  var searchTerm by rememberSaveable { mutableStateOf("") }
  var isLoading by rememberSaveable { mutableStateOf(false) }
  val lifecycleOwner = LocalLifecycleOwner.current
  logcat("ProgressComposable") { "how" }
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

    SearchComposable(
      searchTerm,
      onValueChanged = { newValue ->
        searchTerm = newValue
      },
      onButtonClicked = {
        isLoading = true
        bookViewModel.getBooks(searchTerm)
      })

    ProgressComposable(isLoading)

    collectBooks(
      lifecycleOwner = lifecycleOwner,
      bookViewModel = bookViewModel,
      updateLoading = {
        isLoading = false
      })

  }
}

@Composable
fun collectBooks(
  lifecycleOwner: LifecycleOwner,
  bookViewModel: BookViewModel = viewModel(),
  updateLoading: () -> Unit
) {
  val booksFlow = bookViewModel.books
  val bookFlowLifeCycleAware = remember(booksFlow, lifecycleOwner) {
    logcat("ProgressComposable") { "update the value of loading books" }
    booksFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
  }
  val books by bookFlowLifeCycleAware.collectAsState(initial = emptyList())
  if (books.isNotEmpty()) {
    BookList(books = books)
    updateLoading()
  }


}

@Composable
fun collectError(
  lifecycleOwner: LifecycleOwner,
  bookViewModel: BookViewModel = viewModel()
) {
  val errorFlow = bookViewModel.errorMessage
  val errorFlowLifeCycleAware = remember(errorFlow, lifecycleOwner) {
    logcat("SearchScreen") { "update the value of loading errors" }
    errorFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
  }
  val errorMessage by errorFlowLifeCycleAware.collectAsState(initial = null)

  if (errorMessage != null) {
    // Show error view
    logcat("SearchScreen") { "An error $errorMessage" }
  }
}
