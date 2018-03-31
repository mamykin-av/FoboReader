package ru.mamykin.foboreader.di.component

import dagger.Subcomponent
import ru.mamykin.foboreader.di.scope.FragmentScope
import ru.mamykin.foboreader.ui.store.BooksStoreFragment

@FragmentScope
@Subcomponent(modules = [])
interface BooksStoreComponent {

    fun inject(fragment: BooksStoreFragment)
}