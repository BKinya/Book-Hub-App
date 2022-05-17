package com.beatrice.bookhubapp.util

sealed class FetchBooksState<out T: Any> {
  data class Loading<out T: Any>(val message: String): FetchBooksState<String>()
  data class Success<out T: Any>(val value: T): FetchBooksState<T>()
  data class Error<out T: Any>(val error: String): FetchBooksState<Nothing>()
}