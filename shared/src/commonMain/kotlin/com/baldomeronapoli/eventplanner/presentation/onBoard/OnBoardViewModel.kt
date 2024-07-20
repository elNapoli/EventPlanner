package com.baldomeronapoli.eventplanner.presentation.onBoard

import com.baldomeronapoli.eventplanner.constants.Preferences
import com.baldomeronapoli.eventplanner.presentation.core.BaseViewModel
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.Effect
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.UiState
import com.rickclephas.kmp.observableviewmodel.launch
import com.russhwolf.settings.Settings

class OnBoardViewModel(
    private val preferences: Settings,

    ) : BaseViewModel<UiState, UiIntent, Effect>(
    UiState.initialUiState(preferences.getBoolean(Preferences.SHOW_ONBOARDING, true))
) {
    private fun hideOnboarding() = scope.launch {
        preferences.putBoolean(Preferences.SHOW_ONBOARDING, false)
        sendEffect(Effect.GoToAuthGraph)
    }


    override fun handleIntent(uiIntent: UiIntent) {
        when (uiIntent) {
            UiIntent.CompleteOnboarding -> hideOnboarding()
        }
    }
}