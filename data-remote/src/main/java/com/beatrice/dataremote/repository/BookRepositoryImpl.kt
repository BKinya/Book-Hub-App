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
}