package ru.mamykin.foboreader.ui.dropbox.list

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_file.view.*
import ru.mamykin.foboreader.R
import ru.mamykin.foboreader.entity.DropboxFile
import ru.mamykin.foboreader.extension.attributes
import ru.mamykin.foboreader.extension.isFictionBook
import ru.mamykin.foboreader.extension.isVisible

class DropboxFileViewHolder(itemView: View,
                            onItemClickFunc: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { onItemClickFunc(adapterPosition) }
    }

    fun bind(file: DropboxFile, position: Int, loadingItemPosition: Int) = when (position) {
        loadingItemPosition -> bindLoadingFile()
        else -> bindFile(file)
    }

    private fun bindLoadingFile() {
        itemView.pbLoading.isVisible = true
    }

    private fun bindFile(file: DropboxFile) {
        itemView.tvFileName.text = file.name
        itemView.pbLoading.isVisible = false
        when {
            file.isDirectory -> bindFolder()
            file.isFictionBook -> bindFictionBook(file)
            else -> bindOtherFile(file)
        }
    }

    private fun bindOtherFile(file: DropboxFile) {
        itemView.ivFileType.setImageResource(R.drawable.ic_file)
        itemView.tvFileAttributes.visibility = View.VISIBLE
        itemView.tvFileAttributes.text = file.attributes
    }

    private fun bindFictionBook(file: DropboxFile) {
        itemView.ivFileType.setImageResource(R.drawable.ic_book)
        itemView.tvFileAttributes.visibility = View.VISIBLE
        itemView.tvFileAttributes.text = file.attributes
    }

    private fun bindFolder() {
        itemView.ivFileType.setImageResource(R.drawable.ic_folder)
        itemView.tvFileAttributes.visibility = View.GONE
    }
}