package com.beatrice.dataremote.testUtil

import com.beatrice.dataremote.models.*
import com.beatrice.domain.models.Book

val tBookResult = BooksResult(
  items = listOf(
    Item(
      VolumeInfo(
        allowAnonLogging = false,
        subtitle = "",
        title = "Legacy",
        authors = listOf("Nora Roberts"),
        averageRating = 3.5F,
        canonicalVolumeLink = "jkl",
        description = "#1 bestselling author Nora Roberts presents a new novel of a mother and daughter, of romance and ambition, and a traumatic past reawakened in Legacy.",
        categories = listOf("pqr", "stu"),
        contentVersion = "3.6",
        imageLinks = ImageLinks(
          thumbnail = "http://books.google.com/books/content?id=BXK_zQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",
          smallThumbnail = "http://books.google.com/books/content?id=BXK_zQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ),
        language = "cba",
        industryIdentifiers = emptyList(),
        infoLink = "fed",
        publisher = "St. Martin's Press",
        maturityRating = "zyx",
        pageCount = 400,
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
        publishedDate = "2021-05-25",
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
  authors = "Nora Roberts",
  description = "#1 bestselling author Nora Roberts presents a new novel of a mother and daughter, of romance and ambition, and a traumatic past reawakened in Legacy.",
  averageRating = 3.5F,
  imageLink = "http://books.google.com/books/content?id=BXK_zQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
)