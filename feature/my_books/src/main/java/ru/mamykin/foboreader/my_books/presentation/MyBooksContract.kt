package ru.mamykin.foboreader.my_books.presentation

import ru.mamykin.foboreader.common_book_info.domain.model.BookInfo
import ru.mamykin.foboreader.my_books.domain.model.SortOrder

sealed class Action {
    object Loading : Action()
    data class BooksLoaded(val books: List<BookInfo>) : Action()
}

data class ViewState(
    val isLoading: Boolean = false,
    val books: List<BookInfo> = emptyList()
)

sealed class Event {
    data class RemoveBook(val id: Long) : Event()
    data class SortBooks(val sortOrder: SortOrder) : Event()
    data class FilterBooks(val query: String) : Event()
}

sealed class Effect