package ru.mamykin.foboreader.ui

import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.mamykin.core.data.SettingsStorage
import ru.mamykin.core.platform.Navigator
import ru.mamykin.core.ui.BaseActivity
import ru.mamykin.core.ui.UiUtils
import ru.mamykin.foboreader.R
import ru.mamykin.foboreader.navigation.NavigatorImpl

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val settingsStorage: SettingsStorage by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        initRouter()
        initTheme()
    }

    fun openTab(tab: TabsFragment.Tab) {
        supportFragmentManager.findFragmentById(R.id.fr_main_nav_host)
                ?.childFragmentManager
                ?.fragments
                ?.firstOrNull()
                ?.let { it as? TabsFragment }
                ?.openTab(tab)
    }

    private fun initRouter() {
        loadKoinModules(module(override = true) {
            single<Navigator> { NavigatorImpl(this@MainActivity) }
        })
    }

    private fun initTheme() {
        UiUtils.enableNightMode(settingsStorage.isNightTheme)
    }

    fun showSnackbar(@StringRes messageResId: Int) {
        Snackbar.make(cl_main, getString(messageResId), Snackbar.LENGTH_SHORT)
    }
}