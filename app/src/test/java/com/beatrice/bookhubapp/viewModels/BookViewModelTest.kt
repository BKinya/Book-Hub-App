package com.beatrice.bookhubapp.viewModels

import com.beatrice.bookhubapp.util.ApiExceptionGetBooksUseCase
import com.beatrice.bookhubapp.util.ErrorMessages.GENERAL_API_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.NO_INTERNET_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.SERVICE_UNAVAILABLE_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.FakeGetBooksUseCase
import com.beatrice.bookhubapp.util.NetworkExceptionGetBooksUseCase
import com.beatrice.bookhubapp.util.ServerExceptionGetBooksUseCase
import com.beatrice.bookhubapp.viewmodels.BookViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookViewModelTest {

  private val testDispatcher = UnconfinedTestDispatcher()
  private val searchTerm = "Paulo Coelho"

  @Test
  fun shouldUpdateBookItemsStateFlowInstance() = runBlocking {
    // Arrange
    val getBooksUseCase = FakeGetBooksUseCase()
    val bookViewModel = BookViewModel(getBooksUseCase, testDispatcher)
    // Act
    bookViewModel.getBooks(searchTerm)
    // Assert
    val book = bookViewModel.books.first()!!
    assertThat(book.size, `is`(1))
    assertThat(book[0].title, `is`("ABC"))
    assertThat(book[0].averageRating, `is`(4.5F))
    assertThat(book[0].pageCount, `is`(200))
  }

  @Test
  fun shouldUpdateNetworkErrorStateFlowInstance() = runBlocking {
    // Arrange
    val getBooksUseCase = NetworkExceptionGetBooksUseCase()
    val bookViewModel = BookViewModel(getBooksUseCase, testDispatcher)
    // Act
    bookViewModel.getBooks(searchTerm)
    // Assert
    val error = bookViewModel.internetErrorMessage.first()
    assertThat(error, `is`(NO_INTERNET_ERROR_MESSAGE))
  }

  @Test
  fun shouldUpdateServerErrorStateFlowInstance() = runBlocking{
   // Arrange
    val getBooksUseCase = ServerExceptionGetBooksUseCase()
    val bookViewModel = BookViewModel(getBooksUseCase, testDispatcher)
   // Act
    bookViewModel.getBooks(searchTerm)
   // Assert
    val error = bookViewModel.serverErrorMessage.first()
    assertThat(error, `is`(SERVICE_UNAVAILABLE_ERROR_MESSAGE))
  }

  @Test
  fun shouldUpdateApiErrorStateFlowInstance() = runBlocking{
   // Arrange
    val getBooksUseCase = ApiExceptionGetBooksUseCase()
    val bookViewModel = BookViewModel(getBooksUseCase, testDispatcher)
   // Act
    bookViewModel.getBooks(searchTerm)
   // Assert
    val error = bookViewModel.generalErrorMessage.first()
    assertThat(error, `is`(GENERAL_API_ERROR_MESSAGE))
  }
}