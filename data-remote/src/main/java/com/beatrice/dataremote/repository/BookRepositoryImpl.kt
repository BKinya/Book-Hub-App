package com.beatrice.dataremote.repository

import com.beatrice.dataremote.api.BooksApiService
import com.beatrice.dataremote.util.toDomain
import com.beatrice.domain.repository.BookRepository
import kotlinx.coroutines.flow.flow

class BookRepositoryImpl (
  private val booksApiService: BooksApiService
    ): BookRepository {
  override suspend fun getBooks(searchTerm: String)= flow {
    val result = booksApiService.getBooks(searchTerm)
    val books = result.toDomain()
    emit(books)
  }

  /**
   * TODO: Start here
   * Error handling in flow
   * - https://proandroiddev.com/from-rxjava-to-kotlin-flow-error-handling-da1f6a4f2708
   * - https://elizarov.medium.com/exceptions-in-kotlin-flows-b59643c940fb
   * - https://blog.mindorks.com/exception-handling-in-kotlin-flow
   * And share what you'll learn or the best blog on Twitter
   */
}