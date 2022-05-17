package com.beatrice.bookhubapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrice.domain.models.Book
import com.beatrice.domain.usecases.GetBooksUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import logcat.logcat


class BookViewModel(
  private val getBooksUseCaseImpl: GetBooksUseCase,
  private val dispatcher: CoroutineDispatcher
) : ViewModel() {



  private val bookItems = MutableStateFlow<List<Book>>(emptyList())
  val books: StateFlow<List<Book>>
    get() = bookItems

  private val error = MutableStateFlow<String?>(null)
  val errorMessage: StateFlow<String?>
    get() = error


  fun getBooks(searchTerm: String) {
    viewModelScope.launch(dispatcher) {
      getBooksUseCaseImpl.getBooks(searchTerm)
        .catch { e ->
          logcat("ProgressComposable") { "Exception ${e.message}" }
          error.value = "Something went wrong! Try again later"
        }
        .collect { books ->
          logcat("ProgressComposable") { "Books => ${books.size}" }
          bookItems.value = books
        }
    }
  }



}
