package com.beatrice.dataremote.models

data class VolumeInfo(
    val allowAnonLogging: Boolean,
    val authors: List<String>?,
    val averageRating: Float,
    val canonicalVolumeLink: String,
    val categories: List<String>,
    val contentVersion: String,
    val imageLinks: ImageLinks?,
    val industryIdentifiers: List<IndustryIdentifier>,
    val infoLink: String,
    val language: String,
    val maturityRating: String,
    val pageCount: Int,
    val panelizationSummary: PanelizationSummary,
    val previewLink: String,
    val printType: String,
    val publishedDate: String?,
    val ratingsCount: Int,
    val readingModes: ReadingModes,
    val title: String?,
    val subtitle: String?,
    val publisher: String?,
    val description: String?
)