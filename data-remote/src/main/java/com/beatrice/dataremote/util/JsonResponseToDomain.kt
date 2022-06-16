package com.beatrice.dataremote.util

import com.beatrice.dataremote.models.BooksResult
import com.beatrice.domain.models.Book

fun BooksResult.toDomain(): List<Book> {
  return this.items.map {
    val bookInfo = it.volumeInfo
    var bookTitle = bookInfo.title
    bookInfo.subtitle?.let { subTitle ->
      bookTitle = "${bookInfo.title}: $subTitle"
    }
    Book(
      title = bookTitle ?: "",
      authors = bookInfo.authors?.joinToString() ?: "",
      averageRating = bookInfo.averageRating,
      description = bookInfo.description,
      imageLink = bookInfo.imageLinks?.thumbnail?: "",
      pageCount = bookInfo.pageCount,
      publisher = bookInfo.publisher ?:"",
      publishingDate = bookInfo.publishedDate ?: ""
    )
  }
}