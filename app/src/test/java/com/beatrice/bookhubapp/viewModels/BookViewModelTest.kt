package com.beatrice.bookhubapp.viewModels

import com.beatrice.bookhubapp.util.ExceptionGetBooksUseCase
import com.beatrice.bookhubapp.util.FakeGetBooksUseCase
import com.beatrice.bookhubapp.viewmodels.BookViewModel
import com.beatrice.domain.usecases.GetBooksUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
    val book = bookViewModel.books.first()
    assertThat(book.size, `is`(1))
    assertThat(book[0].title, `is`("ABC"))
    assertThat(book[0].averageRating, `is`(4.5F))
    assertThat(book[0].pageCount, `is`(200))
  }

  @Test
  fun shouldUpdateErrorStateFlowInstance() = runBlocking {
    // Arrange
    val getBooksUseCase = ExceptionGetBooksUseCase()
    val bookViewModel = BookViewModel(getBooksUseCase, testDispatcher)
    // Act
    bookViewModel.getBooks(searchTerm)
    // Assert
    val error = bookViewModel.errorMsg.first()
    assertThat(error, `is`("Something went wrong! Try again later"))
  }
}