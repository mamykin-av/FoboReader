package ru.mamykin.foboreader.core.extension

import android.view.Menu
import android.view.View
import android.widget.SeekBar
import android.widget.Switch
import androidx.annotation.CheckResult
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import reactivecircus.flowbinding.android.widget.SeekBarChangeEvent
import reactivecircus.flowbinding.android.widget.changeEvents
import reactivecircus.flowbinding.android.widget.checkedChanges

fun View.showPopupMenu(@MenuRes menuRes: Int, vararg clicks: Pair<Int, () -> Unit>) {
    PopupMenu(context, this).apply {
        setOnMenuItemClickListener {
            clicks.find { (id, _) -> it.itemId == id }?.second?.invoke()
            return@setOnMenuItemClickListener true
        }
        inflate(menuRes)
        show()
    }
}

fun SeekBar.changeProgressEvents(emitImmediately: Boolean = false): Flow<Int> =
    changeEvents(emitImmediately)
        .filter { it is SeekBarChangeEvent.ProgressChanged && it.fromUser }
        .map { it.view.progress }

fun Switch.manualCheckedChanges(): Flow<Boolean> =
    checkedChanges()
        .filter { this.isPressed }

fun Menu.getSearchView(@IdRes itemId: Int): SearchView {
    val searchItem = findItem(itemId)
    return searchItem.actionView as SearchView
}

@CheckResult
fun SearchView.queryChanges(): Flow<String?> = callbackFlow<String?> {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            offer(newText)
            return true
        }
    })
    awaitClose { setOnQueryTextListener(null) }
}.conflate()