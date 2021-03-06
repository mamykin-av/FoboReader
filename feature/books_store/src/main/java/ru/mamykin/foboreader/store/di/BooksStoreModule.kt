package ru.mamykin.foboreader.store.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.mamykin.foboreader.store.data.BooksStoreRepository
import ru.mamykin.foboreader.store.data.network.FileDownloader
import ru.mamykin.foboreader.store.domain.usecase.DownloadBook
import ru.mamykin.foboreader.store.domain.usecase.FilterStoreBooks
import ru.mamykin.foboreader.store.domain.usecase.GetStoreBooks
import ru.mamykin.foboreader.store.domain.usecase.ReloadStoreBooks
import ru.mamykin.foboreader.store.presentation.BooksStoreViewModel

val booksStoreModule = module {
    single { NetworkDependencies.client() }
    single { NetworkDependencies.service(get()) }
    single { BooksStoreRepository(get()) }
    factory { FileDownloader(androidContext(), get()) }
    factory { ReloadStoreBooks(get()) }
    factory { FilterStoreBooks(get()) }
    factory { GetStoreBooks(get()) }
    factory { DownloadBook(get()) }
    viewModel { BooksStoreViewModel(get(), get(), get(), get(), get()) }
}