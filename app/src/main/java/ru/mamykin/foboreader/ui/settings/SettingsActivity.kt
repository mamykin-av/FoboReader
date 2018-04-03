package ru.mamykin.foboreader.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_settings.*
import ru.mamykin.foboreader.R
import ru.mamykin.foboreader.ui.global.UiUtils
import ru.mamykin.foboreader.presentation.settings.SettingsPresenter
import ru.mamykin.foboreader.presentation.settings.SettingsView
import ru.mamykin.foboreader.ui.global.BaseActivity
import ru.mamykin.foboreader.ui.global.YesNoDialogFragment
import javax.inject.Inject

/**
 * Страница настроек
 */
class SettingsActivity : BaseActivity(), SeekBar.OnSeekBarChangeListener, SettingsView {

    companion object {

        const val DROPBOX_LOGOUT_DIALOG_TAG = "dropbox_dialog_tag"

        fun getStartIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }

    override val layout: Int = R.layout.activity_settings

    @Inject
    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar(getString(R.string.settings), true)
        initClickListeners()
    }

    override fun injectDependencies() {
        super.injectDependencies()
        getAppComponent().getSettingsComponent().inject(this)
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        presenter.onBrightnessProgressChanged(progress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        // Do nothing
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        // Do nothing
    }

    override fun setupBrightness() {
    }

    override fun setDropboxAccount(account: String?) {
        tvDropboxAccount.text = account
    }

    override fun setContentSizeText(size: Int) {
        tvTextSize.text = size.toString()
    }

    override fun setBrightnessPos(position: Int) {
        seekbarBright.progress = position
    }

    override fun setAutoBrightnessChecked(enabled: Boolean) {
        switchBrightAuto.isChecked = enabled
    }

    override fun setBrightnessControlEnabled(enabled: Boolean) {
        seekbarBright.isEnabled = enabled
    }

    override fun setNightThemeEnabled(enabled: Boolean) {
        switchNightTheme.isChecked = enabled
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setHomeAsUpEnabled(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
    }

    override fun restartActivity() {
        UiUtils.restartActivity(this)
    }

    private fun initClickListeners() {
        seekbarBright.setOnSeekBarChangeListener(this)
        switchNightTheme.setOnCheckedChangeListener { _, c -> presenter.onNightThemeEnabled(c) }
        switchBrightAuto.setOnCheckedChangeListener { _, c -> presenter.onAutoBrightnessEnabled(c) }
        btnDropboxLogout.setOnClickListener { showDropboxLogoutDialog() }
    }

    private fun showDropboxLogoutDialog() {
        val title = getString(R.string.dropbox_logout_title)
        val message = getString(R.string.dropbox_logout_message)
        YesNoDialogFragment.newInstance(title, message, presenter::onDropboxLogoutSelected, null)
                .show(supportFragmentManager, DROPBOX_LOGOUT_DIALOG_TAG)
    }
}