package com.beatrice.bookhubapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrice.domain.models.Book
import com.beatrice.domain.usecases.GetBooksUseCase
import com.beatrice.domain.usecases.GetBooksUseCaseImpl
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
  val books: StateFlow<List<Book>> = bookItems

  private val error = MutableStateFlow<String>("")
  val errorMsg: StateFlow<String> = error

  fun getBooks(searchTerm: String) {
    viewModelScope.launch(dispatcher) {
      getBooksUseCaseImpl.getBooks(searchTerm)
        .catch { e ->
          logcat("BookViewModel") { "Exception ${e.message}" }
          error.value = "Something went wrong! Try again later"
        }
        .collect { books ->
          bookItems.value = books
        }
    }

  }
}
