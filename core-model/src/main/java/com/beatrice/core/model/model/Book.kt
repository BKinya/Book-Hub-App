package com.beatrice.core.model.model

/**
* Model classes used throughout the app
*/
data class Book(
    val title: String,
    val authors: String,
    val pageCount: Int,
    val averageRating: Float,
    val imageLink: String,
    val publishingDate: String,
    val publisher: String,
    val description: String,
)