package ru.mamykin.foboreader.settings.domain.model

// TODO: move to presentation
sealed class SettingsItem {
    data class NightTheme(val isEnabled: Boolean) : SettingsItem()
    data class Brightness(val brightness: Int) : SettingsItem()
    data class ReadTextSize(val textSize: Int) : SettingsItem()
    data class TranslationColor(val colorCode: String) : SettingsItem()
    data class AppLanguage(val languageCode: String) : SettingsItem()
}