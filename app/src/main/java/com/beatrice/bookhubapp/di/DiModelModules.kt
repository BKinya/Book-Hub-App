package com.beatrice.bookhubapp.di

import com.beatrice.bookhubapp.viewmodels.BookViewModel
import com.beatrice.dataremote.api.BooksApiService
import com.beatrice.dataremote.api.createOkClient
import com.beatrice.dataremote.api.createRetrofit
import com.beatrice.dataremote.repository.BookRepositoryImpl
import com.beatrice.domain.repository.BookRepository
import com.beatrice.domain.usecases.GetBooksUseCase
import com.beatrice.domain.usecases.GetBooksUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModules = module {
  factory { Dispatchers.IO }
  viewModel { BookViewModel(get(), get()) }
}

val domainModules = module {
  factory<GetBooksUseCase> { GetBooksUseCaseImpl(get()) }
}

val dataRemoteModules = module {
  factory<BookRepository> { BookRepositoryImpl(get())  }
  single { createOkClient() }
  single { createRetrofit(baseUrl = "https://www.googleapis.com/books/v1/", get()) }
  single<BooksApiService> {(get() as Retrofit).create(BooksApiService::class.java)  }
}


val dataLocalModules = module {  }