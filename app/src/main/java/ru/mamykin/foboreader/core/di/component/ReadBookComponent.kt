package ru.mamykin.foboreader.core.di.component

import dagger.Subcomponent
import ru.mamykin.foboreader.core.di.modules.ReadBookModule
import ru.mamykin.foboreader.core.di.scope.ActivityScope
import ru.mamykin.foboreader.presentation.readbook.ReadBookActivity

@ActivityScope
@Subcomponent(modules = [ReadBookModule::class])
interface ReadBookComponent {

    fun inject(activity: ReadBookActivity)
}