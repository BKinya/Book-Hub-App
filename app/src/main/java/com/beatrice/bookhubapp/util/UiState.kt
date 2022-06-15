package com.beatrice.bookhubapp.util

sealed class UiState<out T: Any>{
  data class Loading(val message: String = "Loading"): UiState<Nothing>()
  data class Success<out T : Any>(val data: T?) : UiState<T>()
  data class Error(val message: String): UiState<Nothing>()
}
