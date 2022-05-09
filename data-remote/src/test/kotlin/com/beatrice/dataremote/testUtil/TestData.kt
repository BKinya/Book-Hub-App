package com.beatrice.dataremote.testUtil

import com.beatrice.dataremote.models.*
import com.beatrice.domain.models.Book

val tBookResult = BooksResult(
  items = listOf(
    Item(
      VolumeInfo(
        allowAnonLogging = false,
        title = "ABC",
        authors = listOf("def", "ghi"),
        averageRating = 4.5F,
        canonicalVolumeLink = "jkl",
        description = "mno",
        categories = listOf("pqr", "stu"),
        contentVersion = "3.6",
        imageLinks = ImageLinks(
          thumbnail = "xyz",
          smallThumbnail = "xyz"
        ),
        language = "cba",
        industryIdentifiers = emptyList(),
        infoLink = "fed",
        publisher = "ihg",
        maturityRating = "zyx",
        pageCount = 200,
        previewLink = "lkj",
        printType = "yuv",
        panelizationSummary = PanelizationSummary(
          containsEpubBubbles = false,
          containsImageBubbles = true
        ),
        readingModes = ReadingModes(
          image = false,
          text = true
        ),
        publishedDate = "123",
        ratingsCount = 5
      )
    )
  )
)

val tBook = Book(
  title = "ABC",
  pageCount = 200,
  publisher = "ihg",
  publishingDate = "123",
  authors = listOf("def", "ghi"),
  description = "mno",
  averageRating = 4.5F,
  imageLink = "xyz"
)