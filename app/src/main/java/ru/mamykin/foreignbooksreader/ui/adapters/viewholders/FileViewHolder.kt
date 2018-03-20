package ru.mamykin.foreignbooksreader.ui.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import ru.mamykin.foreignbooksreader.R

class FileViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
    @BindView(R.id.tvFileName)
    var tvFileName: TextView? = null
    @BindView(R.id.tvFileAttributes)
    var tvFileAttributes: TextView? = null
    @BindView(R.id.ivFileType)
    var ivFileType: ImageView? = null
    @BindView(R.id.pbLoading)
    var pbLoading: View? = null

    init {

        ButterKnife.bind(this, itemView)
        itemView.setOnClickListener { v -> listener.onItemClick(adapterPosition) }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}