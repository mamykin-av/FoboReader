package ru.mamykin.foboreader.data.repository

import ru.mamykin.foboreader.data.database.BookDao
import ru.mamykin.foboreader.data.database.BookDatabaseHelper
import ru.mamykin.foboreader.data.model.FictionBook
import rx.Completable
import rx.Single
import javax.inject.Inject

class BookRepository @Inject constructor(
        private val bookDbHelper: BookDatabaseHelper
) {
    fun getBookInfo(bookId: Int): Single<FictionBook> {
        return Single.just(bookDbHelper.getBookDao()!!.getBook(bookId))
    }

    fun removeBook(bookId: Int): Completable {
        return Completable.fromCallable {
            val bookDao = bookDbHelper.getBookDao()!!
            val book = bookDao.getBook(bookId)
            return@fromCallable bookDao.delete(book)
        }
    }

    fun getBooks(searchQuery: String, sortOrder: BookDao.SortOrder): Single<List<FictionBook>> {
        return Single.fromCallable {
            val bookDao = bookDbHelper.getBookDao()!!
            return@fromCallable bookDao.getBooksList(searchQuery, sortOrder)
        }
    }

    fun getBook(bookId: Int): Single<FictionBook> {
        return Single.fromCallable {
            val bookDao = bookDbHelper.getBookDao()!!
            return@fromCallable bookDao.getBook(bookId)
        }
    }
}