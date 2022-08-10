package com.beatrice.core.network.retrofit

import com.beatrice.core.network.BookRemoteDataSource
import com.beatrice.core.network.model.BooksResult
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Implementation of [BookRemoteDataSource] using Retrofit
 */
val baseUrl = "https://www.googleapis.com/books/v1/" // TODO: Move this to build configs
class RetrofitBookNetwork: BookRemoteDataSource{

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient =  OkHttpClient.Builder()
    .addInterceptor(httpLoggingInterceptor)
    .build()

    private val apiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(BooksApiService::class.java)

    override suspend fun fetchBooks(
        searchTerm: String,
        items: String,
        orderBy: String
    ): Response<BooksResult>  = apiService.getBooks(searchTerm,items, orderBy)

}
