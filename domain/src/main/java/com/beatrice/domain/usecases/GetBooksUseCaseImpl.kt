package com.beatrice.domain.usecases

import com.beatrice.core.util.NetworkResult
import com.beatrice.domain.models.Book
import com.beatrice.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

interface GetBooksUseCase {
  suspend fun getBooks(searchTerm: String): Flow<NetworkResult<List<Book>>>
}

class GetBooksUseCaseImpl(
  private val bookRepository: BookRepository
) : GetBooksUseCase{
  override suspend fun getBooks(searchTerm: String) = bookRepository.getBooks(searchTerm)

}