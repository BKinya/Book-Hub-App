package com.beatrice.bookhubapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrice.domain.usecases.GetBooksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import logcat.logcat

class BookViewModel (
  private val getBooksUseCase: GetBooksUseCase
    ): ViewModel() {

  fun getBooks(searchTerm: String){
      viewModelScope.launch (Dispatchers.IO){
        val books = getBooksUseCase.getBooks(searchTerm)
        logcat { "Books are $books" }
      }
  }
}