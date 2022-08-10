package com.beatrice.core.network

import com.beatrice.core.network.model.BooksResult
import retrofit2.Response

/**
 * Interface  representing network class in the Book Hub APP
 */
interface BookRemoteDataSource {

    suspend fun fetchBooks(
        searchTerm: String,
        items: String,
        orderBy: String
    ): Response<BooksResult>

}