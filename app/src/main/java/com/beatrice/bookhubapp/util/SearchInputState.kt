package com.beatrice.bookhubapp.util

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver

class SearchInputState(initialText: String) {
  var searchTerm by mutableStateOf(initialText)

  val showClearButton: Boolean
  get() = searchTerm.isNotEmpty() || searchTerm.isNotBlank()

  companion object {
    val Saver: Saver<SearchInputState, *> = listSaver(
      save = { listOf(it.searchTerm) },
      restore = {
        SearchInputState(
          initialText = it[0],
        )
      }
    )
  }
}