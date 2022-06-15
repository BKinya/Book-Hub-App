package com.beatrice.bookhubapp.viewModels

import com.beatrice.bookhubapp.util.*
import com.beatrice.bookhubapp.util.ErrorMessages.GENERAL_API_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.NO_INTERNET_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.SERVICE_UNAVAILABLE_ERROR_MESSAGE
import com.beatrice.bookhubapp.viewmodels.BookViewModel
import com.beatrice.domain.models.Book
import junit.framework.Assert.assertTrue
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
    val result = bookViewModel.booksUiState.first()!!
    assertTrue(result is UiState.Success)
    val books = (result as UiState.Success).data!!
    assertThat(books.size, `is`(1))
    assertThat(books[0].title, `is`("ABC"))
    assertThat(books[0].averageRating, `is`(4.5F))
    assertThat(books[0].pageCount, `is`(200))
  }

  @Test
  fun shouldUpdateNetworkErrorStateFlowInstance() = runBlocking {
    // Arrange
    val getBooksUseCase = NetworkExceptionGetBooksUseCase()
    val bookViewModel = BookViewModel(getBooksUseCase, testDispatcher)
    // Act
    bookViewModel.getBooks(searchTerm)
    // Assert
    val result = bookViewModel.booksUiState.first()!!
    assertTrue(result is UiState.Error)
    val error = (result as UiState.Error).message
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
    val result = bookViewModel.booksUiState.first()!!
    assertTrue(result is UiState.Error)
    val error = (result as UiState.Error).message
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
    val result = bookViewModel.booksUiState.first()!!
    assertTrue(result is UiState.Error)
    val error = (result as UiState.Error).message
    assertThat(error, `is`(GENERAL_API_ERROR_MESSAGE))
  }
}