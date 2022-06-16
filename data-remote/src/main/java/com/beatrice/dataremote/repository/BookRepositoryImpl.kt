package com.beatrice.dataremote.repository


import com.beatrice.core.util.NetworkResult
import com.beatrice.dataremote.api.BooksApiService
import com.beatrice.dataremote.util.toDomain
import com.beatrice.domain.models.Book
import com.beatrice.domain.repository.BookRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException


class BookRepositoryImpl(
  private val booksApiService: BooksApiService
) : BookRepository {
  override suspend fun getBooks(searchTerm: String) = flow {
    val result: NetworkResult<List<Book>> = try {
      val response = booksApiService.getBooks(searchTerm)
      if (response.isSuccessful) {
        val books = response.body()?.toDomain()
        NetworkResult.Success(data = books)
      } else {
        val error = response.message()
        NetworkResult.ApiError(response.toString())
      }
    } catch (e: IOException) {
      NetworkResult.NetworkError(e.message!!)
    } catch (e: Exception) {
      NetworkResult.ServerError(e.message!!)
    }
    emit(result)
  }
}
