package ru.mamykin.foboreader.core.data

import ru.mamykin.foboreader.core.data.database.BookInfoDao
import ru.mamykin.foboreader.core.domain.model.BookInfo

class BookInfoRepository constructor(
        private val bookInfoDao: BookInfoDao
) {
    suspend fun getBooks(query: String?): List<BookInfo> = with(bookInfoDao) {
        val books = if (query.isNullOrEmpty()) getBooks() else findBooks(query)
        return books.map { it.toDomainModel() }
    }

    suspend fun getBook(id: Long): BookInfo? {
        return bookInfoDao.getBook(id)?.toDomainModel()
    }

    suspend fun removeBook(id: Long) {
        bookInfoDao.remove(id)
    }
}