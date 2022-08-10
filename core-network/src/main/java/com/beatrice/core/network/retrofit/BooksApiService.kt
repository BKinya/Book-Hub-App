package com.beatrice.core.network.retrofit

import com.beatrice.core.network.model.BooksResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit Api declaration
 */
interface BooksApiService {

    @GET("volumes")
    suspend fun getBooks(
        @Query("q") searchTerm: String,
        @Query("fields") items: String = "items(volumeInfo)",
        @Query("orderby") orderBy: String = "relevance"
    ): Response<BooksResult>
}