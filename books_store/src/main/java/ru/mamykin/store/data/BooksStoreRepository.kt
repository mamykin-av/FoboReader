package ru.mamykin.store.data

import ru.mamykin.store.data.model.BooksStoreResponse

class BooksStoreRepository constructor(
        private val booksService: BooksStoreService
) {
    suspend fun getBooks(): BooksStoreResponse {
        return booksService.getBooksAsync().await()
    }
}