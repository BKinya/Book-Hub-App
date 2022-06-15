package com.beatrice.bookhubapp.views.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.beatrice.bookhubapp.util.UiState
import com.beatrice.bookhubapp.viewmodels.BookViewModel
import com.beatrice.domain.models.Book
import logcat.logcat

@Composable
fun BooksStateComposable(
  lifecycleOwner: LifecycleOwner,
  bookViewModel: BookViewModel = viewModel(),
) {
  val booksFlow = bookViewModel.booksUiState
  val bookFlowLifeCycleAware = remember(booksFlow, lifecycleOwner) {
    booksFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
  }
  val booksUiState by bookFlowLifeCycleAware.collectAsState(null)
  when (booksUiState) {
    is UiState.Loading -> {
      ProgressIndicatorComposable()
    }
    is UiState.Success -> {
      val books =
        (booksUiState as UiState.Success<List<Book>>).data
      books?.let {
        BookListComposable(books = books)
      }
    }
    is UiState.Error -> {
      ErrorMessageComposable(errorMessage = (booksUiState as UiState.Error).message)
    }
    else -> {
      logcat("SearchScreenLogs") { " Preparing " }
    }
  }
}
