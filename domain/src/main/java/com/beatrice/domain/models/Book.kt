package com.beatrice.domain.models

data class Book(
  val title: String,
  val authors: List<String>,
  val pageCount: Int,
  val averageRating: Float,
  val imageLink: String,
  val publishingDate: String,
  val publisher: String?,
  val description: String?,
  )