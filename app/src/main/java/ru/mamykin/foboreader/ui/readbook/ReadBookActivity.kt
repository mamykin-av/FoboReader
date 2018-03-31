package ru.mamykin.foboreader.ui.readbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_read_book.*
import ru.mamykin.foboreader.R
import ru.mamykin.foboreader.extension.addGlobalLayoutListener
import ru.mamykin.foboreader.extension.isVisible
import ru.mamykin.foboreader.presentation.readbook.ReadBookPresenter
import ru.mamykin.foboreader.presentation.readbook.ReadBookView
import ru.mamykin.foboreader.ui.global.BaseActivity
import ru.mamykin.foboreader.ui.global.control.SwipeableTextView
import javax.inject.Inject

/**
 * Страница чтения книги
 */
class ReadBookActivity : BaseActivity(), ReadBookView, SwipeableTextView.SwipeableListener {

    companion object {

        private const val BOOK_PATH_EXTRA = "book_path_extra"
        private const val BOOK_ID_EXTRA = "book_id_extra"

        fun getStartIntent(context: Context, bookId: Int): Intent {
            val readIntent = Intent(context, ReadBookActivity::class.java)
            readIntent.putExtra(BOOK_ID_EXTRA, bookId)
            return readIntent
        }

        fun getStartIntent(context: Context, bookPath: String): Intent {
            val readIntent = Intent(context, ReadBookActivity::class.java)
            readIntent.putExtra(BOOK_PATH_EXTRA, bookPath)
            return readIntent
        }
    }

    override val layout: Int = R.layout.activity_read_book

    @Inject
    @InjectPresenter
    lateinit var presenter: ReadBookPresenter

    @ProvidePresenter
    internal fun providePresenter(): ReadBookPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvText.setSwipeableListener(this)
    }

    override fun showLoading(show: Boolean) {
        pbLoading.isVisible = show
    }

    override fun displayBookName(name: String) {
        tvName.text = name
    }

    override fun onClick(paragraph: String) {
        presenter.onTranslateParagraphClicked(paragraph)
    }

    override fun onLongClick(word: String) {
        presenter.onWordClicked(word)
    }

    override fun onSwipeLeft() {
        presenter.onSwipeLeft()
    }

    override fun onSwipeRight() {
        presenter.onSwipeRight()
    }

    override fun displayParagraphTranslation(text: String) {
        tvText.setTranslation(text)
    }

    override fun initBookView(title: String, text: String) {
        tvText.addGlobalLayoutListener(presenter::onViewInitCompleted)
    }

    override fun showBookContent(show: Boolean) {
        llBookContent.isVisible = show
    }

    override fun showParagraphLoading(show: Boolean) {
    }

    override fun showWordLoading(show: Boolean) {
    }

    override fun displayCurrentPage(page: Int) {
    }

    override fun displayReadPages(text: Int) {
    }

    override fun displayReadPercent(percent: Float) {
    }

    override fun displaySourceParagraph() {
    }

    override fun displayWordTranslation(originalAndTranslation: Pair<String, String>) {
    }

    override fun displayPageText(text: String) {
    }
}