package ru.mamykin.book_details.domain

import ru.mamykin.book_details.data.BookDetailsRepository
import ru.mamykin.core.data.model.FictionBook

class BookDetailsInteractor constructor(
        private val repository: BookDetailsRepository
) {
    suspend fun getBookInfo(bookPath: String): FictionBook {
        return repository.getBookInfo(bookPath)
    }
}