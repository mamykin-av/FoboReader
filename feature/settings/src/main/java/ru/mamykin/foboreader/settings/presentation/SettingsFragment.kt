package ru.mamykin.foboreader.settings.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.android.view.clicks
import ru.mamykin.foboreader.core.extension.*
import ru.mamykin.foboreader.core.ui.BaseFragment
import ru.mamykin.foboreader.settings.R
import ru.mamykin.foboreader.settings.databinding.FragmentSettingsBinding

@FlowPreview
@ExperimentalCoroutinesApi
class SettingsFragment : BaseFragment<SettingsViewModel, ViewState, Effect>() {

    override val viewModel: SettingsViewModel by viewModel()

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViews()
    }

    private fun initToolbar() = toolbar.apply {
        setTitle(R.string.settings_title)
        navigationIcon = null
    }

    private fun initViews() {
        binding.seekBright.changeProgressEvents()
            .onEach { viewModel.sendEvent(Event.BrightnessChanged(it)) }
            .launchIn(lifecycleScope)
        binding.swNightTheme.manualCheckedChanges()
            .onEach { viewModel.sendEvent(Event.NightThemeChanged(it)) }
            .launchIn(lifecycleScope)
        binding.swBrightAuto.manualCheckedChanges()
            .onEach { viewModel.sendEvent(Event.AutoBrightnessChanged(it)) }
            .launchIn(lifecycleScope)
        binding.btnTextSizeMinus.clicks()
            .onEach { viewModel.sendEvent(Event.DecreaseTextSizeClicked) }
            .launchIn(lifecycleScope)
        binding.btnTextSizePlus.clicks()
            .onEach { viewModel.sendEvent(Event.IncreaseTextSizeClicked) }
            .launchIn(lifecycleScope)
        binding.clTranslationColor.clicks()
            .onEach { viewModel.sendEvent(Event.SelectReadColorClicked) }
            .launchIn(lifecycleScope)
    }

    override fun showState(state: ViewState) {
        progressView.isVisible = state.isLoading
        state.settings?.let {
            showTheme(it.isNightTheme)
            showBrightness(it.isAutoBrightness, it.brightness)
            binding.swBrightAuto.isChecked = it.isAutoBrightness
            binding.tvTextSize.text = it.contentTextSize.toString()
        }
    }

    private fun showTheme(nightTheme: Boolean) {
        binding.swNightTheme.isChecked = nightTheme
        appCompatActivity.nightMode = nightTheme
    }

    private fun showBrightness(autoBrightness: Boolean, brightnessValue: Int) {
        binding.seekBright.isEnabled = !autoBrightness
        binding.seekBright.progress = brightnessValue
    }

    override fun takeEffect(effect: Effect) {
        when (effect) {
            is Effect.OpenSelectReadColorScreen ->
                ColorPickerFragment().show(activity!!.supportFragmentManager, null)
        }
    }
}