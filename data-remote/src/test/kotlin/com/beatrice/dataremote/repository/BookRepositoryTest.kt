package com.beatrice.dataremote.repository

import com.beatrice.core.util.NetworkResult
import com.beatrice.dataremote.api.BooksApiService
import com.beatrice.dataremote.testUtil.getJson
import com.beatrice.dataremote.testUtil.tBook
import io.mockk.coVerify
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BookRepositoryTest {

  private lateinit var mockWebServer: MockWebServer
  private lateinit var apiService: BooksApiService
  private lateinit var okHttpClient: OkHttpClient
  private lateinit var loggingInterceptor: HttpLoggingInterceptor
  private lateinit var repository: BookRepositoryImpl

  @Before
  fun setUp() {
    mockWebServer = MockWebServer()
    loggingInterceptor = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
    okHttpClient = OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()

    apiService = Retrofit.Builder()
      .baseUrl(mockWebServer.url("/"))
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create(BooksApiService::class.java)

    repository = BookRepositoryImpl(
      booksApiService = apiService
    )
  }

  @After
  fun tearDown() {
    mockWebServer.shutdown()
  }

  @Test
  fun `should return a list of Books fetched from the API`() = runBlocking {
    // Arrange
    mockWebServer.enqueue(MockResponse().apply {
      setResponseCode(200)
      setBody(getJson("json/book-result-200.json"))
    })
    // Act
    val searchTerm = "Nora Roberts"
    val result = repository.getBooks(searchTerm)
    // Assert
    result.collect { res ->
      assertTrue(res is NetworkResult.Success)
      val data = (res as NetworkResult.Success).data
      assertNotNull(data)
      assertThat(data?.get(0), `is`(tBook))
    }

  }

}