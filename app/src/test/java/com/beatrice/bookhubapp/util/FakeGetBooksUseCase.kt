package com.beatrice.bookhubapp.util

import com.beatrice.domain.models.Book
import com.beatrice.domain.usecases.GetBooksUseCase
import kotlinx.coroutines.flow.flow

class FakeGetBooksUseCase: GetBooksUseCase {
  override suspend fun getBooks(searchTerm: String) = flow {
    emit(books)
  }
}

class ExceptionGetBooksUseCase: GetBooksUseCase{
  override suspend fun getBooks(searchTerm: String) = flow<List<Book>> {
    throw Exception()
  }

}