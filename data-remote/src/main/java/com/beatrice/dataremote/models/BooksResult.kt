package com.beatrice.dataremote.models

/**
 * Model representation of results received from the remote API
 */
data class BooksResult(
    val items: List<Item>
)