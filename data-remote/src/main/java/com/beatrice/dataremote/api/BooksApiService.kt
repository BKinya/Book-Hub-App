package com.beatrice.dataremote.api

import com.beatrice.dataremote.models.BooksResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface BooksApiService {

  @GET("volumes")
  suspend fun getBooks(
    @Query("q") searchTerm: String,
    @Query("fields") items: String = "items(volumeInfo)",
    @Query("orderby") orderBy: String = "relevance"
  ): Response<BooksResult>
}