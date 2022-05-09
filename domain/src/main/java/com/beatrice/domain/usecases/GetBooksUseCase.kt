package com.beatrice.domain.usecases

import com.beatrice.domain.repository.BookRepository

class GetBooksUseCase(
  private val bookRepository: BookRepository
) {
  suspend fun getBooks(searchTerm: String) = bookRepository.getBooks(searchTerm)
}