package ru.mamykin.foboreader.book_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.mamykin.foboreader.book_details.R
import ru.mamykin.foboreader.book_details.databinding.FragmentBookDetailsBinding
import ru.mamykin.foboreader.book_details.presentation.list.BookInfoAdapter
import ru.mamykin.foboreader.book_details.presentation.model.BookInfoItem
import ru.mamykin.foboreader.common_book_info.domain.model.BookInfo
import ru.mamykin.foboreader.core.extension.showSnackbar
import ru.mamykin.foboreader.core.ui.BaseFragment

class BookDetailsFragment : BaseFragment<BookDetailsViewModel, ViewState, Effect>() {

    companion object {

        private const val EXTRA_BOOK_ID = "BOOK_ID"

        fun bundle(bookId: Long) = Bundle().apply {
            putLong(EXTRA_BOOK_ID, bookId)
        }
    }

    override val viewModel: BookDetailsViewModel by viewModel {
        parametersOf(arguments?.getLong(EXTRA_BOOK_ID) ?: throw IllegalStateException("No book ID!"))
    }

    private val bookInfoAdapter = BookInfoAdapter()
    private lateinit var binding: FragmentBookDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBookDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(R.string.my_books_book_info_title)
        binding.fabRead.setOnClickListener {
            viewModel.sendEvent(Event.ReadBookClicked)
        }
        binding.rvBookInfo.adapter = bookInfoAdapter
    }

    override fun showState(state: ViewState) {
        state.bookInfo?.let(::showBookInfo)
    }

    private fun showBookInfo(book: BookInfo) = binding.apply {
        tvBookName.text = book.title
        tvBookAuthor.text = book.author
        bookInfoAdapter.changeData(
            listOf(
                BookInfoItem(
                    getString(R.string.my_books_bookmarks),
                    getString(R.string.my_books_no_bookmarks)
                ),
                BookInfoItem(
                    getString(R.string.my_books_book_path),
                    book.filePath
                ),
                BookInfoItem(
                    getString(R.string.my_books_current_page),
                    book.currentPage.toString()
                ),
                BookInfoItem(
                    getString(R.string.my_books_book_genre),
                    book.genre
                )
            )
        )
        showBookCover(book.coverUrl)
    }

    private fun showBookCover(coverUrl: String?) {
        Picasso.with(context)
            .load(coverUrl)
            .error(R.drawable.img_no_image)
            .into(binding.ivBookCover, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError() {
                    startPostponedEnterTransition()
                }
            })
    }

    override fun takeEffect(effect: Effect) {
        when (effect) {
            is Effect.ShowSnackbar -> showSnackbar(effect.message)
        }
    }
}