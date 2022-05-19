package com.beatrice.bookhubapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrice.bookhubapp.util.ErrorMessages.GENERAL_API_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.NO_INTERNET_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.SERVICE_UNAVAILABLE_ERROR_MESSAGE
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

  private val bookItems = MutableStateFlow<List<Book>?>(emptyList())
  val books: StateFlow<List<Book>?>
    get() = bookItems

  private val generalError = MutableStateFlow<String?>(null)
  val generalErrorMessage: StateFlow<String?>
    get() = generalError

  private val serverError = MutableStateFlow<String?>(null)
  val serverErrorMessage: StateFlow<String?>
    get() = serverError

  private val internetError = MutableStateFlow<String?>(null)
  val internetErrorMessage: StateFlow<String?>
    get() = internetError


  fun getBooks(searchTerm: String) {
    viewModelScope.launch(dispatcher) {
      getBooksUseCase.getBooks(searchTerm)
        .catch { e ->
          logcat("BookViewModel - getBooks()") { "Exception ${e.message}" }
          generalError.value = GENERAL_API_ERROR_MESSAGE
        }
        .collect { result ->
          when (result) {
            is NetworkResult.Success -> {
              val books = result.data
              logcat("BookViewModel - getBooks()") { "Books => ${books?.size}" }
              bookItems.value = books
            }
            is NetworkResult.NetworkError -> {
              logcat("BookViewModel - getBooks()") { "Exception ${result.error}" }
              internetError.value = NO_INTERNET_ERROR_MESSAGE
            }
            is NetworkResult.ApiError -> {
              logcat("BookViewModel - getBooks()") { "Exception ${result.error}" }
              generalError.value = GENERAL_API_ERROR_MESSAGE
            }
            is NetworkResult.ServerError -> {
              logcat("BookViewModel - getBooks()") { "Exception ${result.error}" }
              serverError.value = SERVICE_UNAVAILABLE_ERROR_MESSAGE
            }
          }
        }
    }
  }
}
