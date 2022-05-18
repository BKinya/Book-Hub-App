package com.beatrice.domain.repository

import com.beatrice.core.util.NetworkResult
import com.beatrice.domain.models.Book
import kotlinx.coroutines.flow.Flow


interface BookRepository {
  suspend fun getBooks(searchTerm: String): Flow<NetworkResult<List<Book>>>
}