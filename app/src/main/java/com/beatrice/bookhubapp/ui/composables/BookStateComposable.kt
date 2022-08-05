package com.beatrice.bookhubapp.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.beatrice.bookhubapp.util.UiState
import com.beatrice.bookhubapp.views.composables.BookListComposable
import com.beatrice.domain.models.Book
import logcat.logcat


@Composable
fun BooksStateComposable(
    booksUiState: UiState<List<Book>>? = UiState.Loading(),
) {
    when (booksUiState) {
        is UiState.Loading -> {
            ProgressIndicatorComposable()
        }
        is UiState.Success -> {
            val books = booksUiState.data
            books?.let {
                BookListComposable(books = books)
            }
        }
        is UiState.Error -> {
            ErrorMessageComposable(errorMessage = (booksUiState).message)
        }
        else -> {
            logcat("BookStateComposable") { "Preparing" }
        }
    }
}
