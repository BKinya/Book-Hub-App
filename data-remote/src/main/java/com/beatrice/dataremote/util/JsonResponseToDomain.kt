package com.beatrice.dataremote.util

import com.beatrice.dataremote.models.BooksResult
import com.beatrice.domain.models.Book

fun BooksResult.toDomain(): List<Book> {
  return this.items.map {
    val bookInfo = it.volumeInfo
    Book(
      title = bookInfo.title ?: "",
      authors = bookInfo.authors ?: emptyList(), // TODO: Check all the nulls
      averageRating = bookInfo.averageRating,
      description = bookInfo.description,
      imageLink = bookInfo.imageLinks?.thumbnail?: "",
      pageCount = bookInfo.pageCount,
      publisher = bookInfo.publisher,
      publishingDate = bookInfo.publishedDate ?: ""
    )
  }
}