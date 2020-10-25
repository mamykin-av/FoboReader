package ru.mamykin.foboreader.my_books.presentation

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.view.isVisible
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.android.view.clicks
import ru.mamykin.foboreader.common_book_info.domain.model.BookInfo
import ru.mamykin.foboreader.core.extension.getSearchView
import ru.mamykin.foboreader.core.extension.queryChanges
import ru.mamykin.foboreader.core.presentation.BaseFragment
import ru.mamykin.foboreader.core.presentation.viewBinding
import ru.mamykin.foboreader.my_books.R
import ru.mamykin.foboreader.my_books.databinding.FragmentMyBooksBinding
import ru.mamykin.foboreader.my_books.databinding.ItemBookBinding
import ru.mamykin.foboreader.my_books.domain.model.SortOrder
import ru.mamykin.foboreader.my_books.navigation.MyBooksNavigator
import ru.mamykin.foboreader.my_books.presentation.list.BookViewHolder

@ExperimentalCoroutinesApi
@FlowPreview
class MyBooksFragment : BaseFragment<MyBooksViewModel, ViewState, Effect>(R.layout.fragment_my_books) {

    override val viewModel: MyBooksViewModel by viewModel()

    private val navigator: MyBooksNavigator by inject()
    private val booksSource = dataSourceTypedOf<BookInfo>()
    private val binding by viewBinding { FragmentMyBooksBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initBooksList()
        viewModel.sendEvent(Event.ScanBooks)
    }

    private fun initToolbar() = toolbar!!.apply {
        title = getString(R.string.my_books_screen_title)
        navigationIcon = null
        inflateMenu(R.menu.menu_books_list)
        menu.findItem(R.id.actionSortName)
            .clicks()
            .onEach { viewModel.sendEvent(Event.SortBooks(SortOrder.ByName)) }
        menu.findItem(R.id.actionSortReaded)
            .clicks()
            .onEach { viewModel.sendEvent(Event.SortBooks(SortOrder.ByReaded)) }
        menu.findItem(R.id.actionSortDate)
            .clicks()
            .onEach { viewModel.sendEvent(Event.SortBooks(SortOrder.ByDate)) }
        initSearchView(menu)
    }

    private fun initSearchView(menu: Menu) = menu.getSearchView(R.id.action_search).apply {
        queryHint = getString(R.string.my_books_menu_search)
        queryChanges()
            .filterNotNull()
            .onEach { viewModel.sendEvent(Event.FilterBooks(it)) }
    }

    private fun initBooksList() {
        binding.rvMyBooks.setup {
            withEmptyView(binding.vNoBooks)
            withDataSource(booksSource)
            withItem<BookInfo, BookViewHolder>(R.layout.item_book) {
                onBind({
                    BookViewHolder(
                        ItemBookBinding.bind(it),
                        navigator::myBooksToBookDetails
                    ) { viewModel.sendEvent(Event.RemoveBook(it)) }
                }) { _, item -> bind(item) }
                onClick { navigator.myBooksToReadBook(item.id) }
            }
        }
    }

    override fun showState(state: ViewState) {
        progressView.isVisible = state.isLoading
        booksSource.set(state.books)
    }
}