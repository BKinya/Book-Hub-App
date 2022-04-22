package com.beatrice.dataremote.repository

import com.beatrice.dataremote.api.BooksApiService
import com.beatrice.dataremote.util.toDomain
import com.beatrice.domain.models.Book
import com.beatrice.domain.repository.BookRepository

class BookRepositoryImpl (
  private val booksApiService: BooksApiService
    ): BookRepository {
  override suspend fun getBooks(searchTerm: String): List<Book> {
    val result = booksApiService.getBooks(searchTerm)
    return result.toDomain()
  }
}