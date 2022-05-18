package com.beatrice.core.util

sealed class NetworkResult<out T : Any> {
  data class Success<out T : Any>(val data: T?) : NetworkResult<T>()
  data class ApiError(val error: String): NetworkResult<Nothing>()
  data class NetworkError(val error: String) : NetworkResult<Nothing>()
  data class ServerError(val error: String) : NetworkResult<Nothing>()
}