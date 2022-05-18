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
  title = "Legacy",
  pageCount = 400,
  publisher = "St. Martin's Press",
  publishingDate = "2021-05-25",
  authors = listOf( "Nora Roberts"),
  description = "#1 bestselling author Nora Roberts presents a new novel of a mother and daughter, of romance and ambition, and a traumatic past reawakened in Legacy.",
  averageRating = 3.5F,
  imageLink = "http://books.google.com/books/content?id=BXK_zQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
)