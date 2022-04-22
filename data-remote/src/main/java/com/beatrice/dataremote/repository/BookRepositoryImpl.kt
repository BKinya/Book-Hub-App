package com.beatrice.dataremote.repository

import com.beatrice.dataremote.api.BooksApiService
import com.beatrice.domain.repository.BookRepository

class BookRepositoryImpl (
  private val booksApiService: BooksApiService
    ): BookRepository {
  override suspend fun getBooks(searchTerm: String): String {
    val books = booksApiService.getBooks(searchTerm)
    return books.items[0].volumeInfo.title
  }
}