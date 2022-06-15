package com.beatrice.bookhubapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrice.bookhubapp.util.ErrorMessages.GENERAL_API_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.NO_INTERNET_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.SERVICE_UNAVAILABLE_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.UiState
import com.beatrice.core.util.NetworkResult
import com.beatrice.domain.models.Book
import com.beatrice.domain.usecases.GetBooksUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import logcat.logcat


class BookViewModel(
  private val getBooksUseCase: GetBooksUseCase,
  private val dispatcher: CoroutineDispatcher
) : ViewModel() {

  private val booksResult = MutableStateFlow<UiState<List<Book>>?>(null)
  val booksUiState: StateFlow<UiState<List<Book>>?>
    get() = booksResult


  fun getBooks(searchTerm: String) {
    viewModelScope.launch(dispatcher) {
      booksResult.value = UiState.Loading()
      getBooksUseCase.getBooks(searchTerm)
        .catch { e ->
          logcat("BookViewModel - getBooks()") { "Exception ${e.message}" }
          booksResult.value = UiState.Error(message = GENERAL_API_ERROR_MESSAGE)
        }
        .collect { result ->
          when (result) {
            is NetworkResult.Success -> {
              val books = result.data
              logcat("BookViewModel - getBooks()") { "Books => ${books?.size}" }
              booksResult.value = UiState.Success(data = books)
            }
            is NetworkResult.NetworkError -> {
              logcat("BookViewModel - getBooks()") { "Exception ${result.error}" }
              booksResult.value = UiState.Error(message = NO_INTERNET_ERROR_MESSAGE)
            }
            is NetworkResult.ApiError -> {
              logcat("BookViewModel - getBooks()") { "Exception ${result.error}" }
              booksResult.value = UiState.Error(message = GENERAL_API_ERROR_MESSAGE)
            }
            is NetworkResult.ServerError -> {
              logcat("BookViewModel - getBooks()") { "Exception ${result.error}" }
              booksResult.value = UiState.Error(message = SERVICE_UNAVAILABLE_ERROR_MESSAGE)
            }
          }
        }
    }
  }
}
