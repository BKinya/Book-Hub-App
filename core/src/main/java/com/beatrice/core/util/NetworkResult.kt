package com.beatrice.core.util

sealed class NetworkResult<out T : Any> {
  data class Success<out T : Any>(val data: T?) : NetworkResult<T>()
  data class ApiError(val error: String) : NetworkResult<Nothing>()
  object NoInternet : NetworkResult<Nothing>()
  object ServerError : NetworkResult<Nothing>()
}