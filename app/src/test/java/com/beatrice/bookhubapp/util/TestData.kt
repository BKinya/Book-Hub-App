package com.beatrice.bookhubapp.util

import com.beatrice.domain.models.Book

val books = listOf(
  Book(
    title = "ABC",
    pageCount = 200,
    publisher = "ihg",
    publishingDate = "123",
    authors = listOf("def", "ghi"),
    description = "mno",
    averageRating = 4.5F,
    imageLink = "xyz"
  )
)