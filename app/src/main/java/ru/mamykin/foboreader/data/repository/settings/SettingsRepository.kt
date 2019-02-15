package ru.mamykin.foboreader.data.repository.settings

import ru.mamykin.foboreader.ui.global.UiUtils
import javax.inject.Inject

class SettingsRepository @Inject constructor(
        private val settingsStorage: SettingsStorage
) {
    fun isNightThemeEnabled(): Boolean = settingsStorage.nightThemeEnabled

    fun isManualBrightnessEnabled(): Boolean = settingsStorage.manualBrightnessEnabled

    fun getManualBrightnessValue(): Float = settingsStorage.manualBrightnessValue

    fun getBookTextSize(): Int = settingsStorage.bookTextSize

    fun getDropboxAccount(): String? = settingsStorage.dropboxAccount

    fun enableNightTheme(enable: Boolean) = UiUtils.enableNightMode(enable).also {
        settingsStorage.nightThemeEnabled = enable
    }

    fun enableAutoBrightness(auto: Boolean) {
        settingsStorage.manualBrightnessEnabled = !auto
    }

    fun changeBrightness(value: Float) {
        settingsStorage.manualBrightnessValue = value
    }

    fun logoutDropbox() {
        settingsStorage.dropboxAccount = null
    }
}