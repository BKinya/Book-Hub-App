package com.beatrice.domain.repository

import com.beatrice.domain.models.Book

interface BookRepository {
  suspend fun getBooks(searchTerm: String): List<Book>
}