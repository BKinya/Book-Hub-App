package com.beatrice.domain.repository

interface BookRepository {
  suspend fun getBooks(searchTerm: String): String
}