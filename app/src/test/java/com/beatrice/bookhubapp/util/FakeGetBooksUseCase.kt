package com.beatrice.bookhubapp.util

import com.beatrice.bookhubapp.util.ErrorMessages.GENERAL_API_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.NO_INTERNET_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.ErrorMessages.SERVICE_UNAVAILABLE_ERROR_MESSAGE
import com.beatrice.core.util.NetworkResult
import com.beatrice.domain.models.Book
import com.beatrice.domain.usecases.GetBooksUseCase
import kotlinx.coroutines.flow.flow

class FakeGetBooksUseCase: GetBooksUseCase {
  override suspend fun getBooks(searchTerm: String) = flow {
    val result = NetworkResult.Success(data = books)
    emit(result)
  }
}

class NetworkExceptionGetBooksUseCase: GetBooksUseCase{
  override suspend fun getBooks(searchTerm: String) = flow{
    val result = NetworkResult.NetworkError(NO_INTERNET_ERROR_MESSAGE)
    emit(result)
  }
}

class ApiExceptionGetBooksUseCase: GetBooksUseCase{
  override suspend fun getBooks(searchTerm: String) = flow{
    val result = NetworkResult. ApiError(GENERAL_API_ERROR_MESSAGE)
    emit(result)
  }
}

class ServerExceptionGetBooksUseCase: GetBooksUseCase{
  override suspend fun getBooks(searchTerm: String) = flow{
    val result = NetworkResult.ServerError(SERVICE_UNAVAILABLE_ERROR_MESSAGE)
    emit(result)
  }
}