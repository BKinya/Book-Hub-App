package com.beatrice.dataremote.repository

import com.beatrice.dataremote.api.BooksApiService
import com.beatrice.dataremote.testUtil.tBook
import com.beatrice.dataremote.testUtil.tBookResult
import com.beatrice.domain.models.Book
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class BookRepositoryTest {

  private val apiService = mockk<BooksApiService>()
  private val repository = BookRepositoryImpl(
    booksApiService =  apiService
  )
  @Test
  fun `should return a list of Books fetched from the API`(){
    // Arrange
    coEvery { apiService.getBooks(any(), any(), any()) } returns tBookResult
    // Act
    val searchTerm = "Paulo Coelho"
    var result: List<Book>
    runBlocking {
     result =  repository.getBooks(searchTerm).first()
    }
    // Assert
    coVerify { apiService.getBooks(searchTerm) }
    assertThat(result.size, `is`(1))
    assertThat(result[0].description, `is`(tBook.description))
    assertThat(result[0].title, `is`(tBook.title))
    assertThat(result[0].pageCount, `is`(tBook.pageCount))
  }

}