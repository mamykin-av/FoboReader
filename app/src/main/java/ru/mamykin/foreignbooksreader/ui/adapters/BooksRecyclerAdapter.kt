package ru.mamykin.foreignbooksreader.ui.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

import javax.inject.Inject

import ru.mamykin.foreignbooksreader.R
import ru.mamykin.foreignbooksreader.ReaderApp
import ru.mamykin.foreignbooksreader.common.UiUtils
import ru.mamykin.foreignbooksreader.models.FictionBook
import ru.mamykin.foreignbooksreader.ui.adapters.viewholders.BookViewHolder

/**
 * Адаптер с книгами на странице "Мои книги"
 */
class BooksRecyclerAdapter(private val listener: OnBookClickListener) : RecyclerView.Adapter<BookViewHolder>() {
    private var booksList: List<FictionBook>? = null
    @Inject
    protected var context: Context? = null

    init {
        this.booksList = ArrayList()
        ReaderApp.component.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val book = LayoutInflater.from(
                parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(book, listener)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = getItem(position)
        holder.ivBookCover!!.setImageBitmap(BitmapFactory
                .decodeResource(context!!.resources, R.drawable.img_no_image))
        holder.tvBookTitle!!.text = item.bookTitle
        holder.tvBookAuthor!!.text = item.bookAuthor
        holder.vProgress!!.setPercents(item.percents)
        holder.tvBookPages!!.text = item.pagesCountString
        holder.tvBookAddedDate!!.text = item.lastOpenString
        UiUtils.setVisibility(holder.vFormat!!, item.bookFormat == FictionBook.Format.FBWT)
    }

    override fun getItemCount(): Int {
        return booksList!!.size
    }

    fun getItem(position: Int): FictionBook {
        return booksList!![position]
    }

    fun changeData(booksList: List<FictionBook>) {
        this.booksList = booksList
        notifyDataSetChanged()
    }

    interface OnBookClickListener {
        fun onBookClicked(position: Int)

        fun onBookAboutClicked(position: Int)

        fun onBookShareClicked(position: Int)

        fun onBookRemoveClicked(position: Int)
    }
}