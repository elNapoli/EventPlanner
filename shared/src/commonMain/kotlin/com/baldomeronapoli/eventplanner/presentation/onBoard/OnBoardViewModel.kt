package com.baldomeronapoli.eventplanner.presentation.onBoard

import com.baldomeronapoli.eventplanner.presentation.core.BaseViewModel
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.Effect
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.UiState
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import com.rickclephas.kmp.observableviewmodel.launch

class OnBoardViewModel(
    private val preferences: SharePreferences,

    ) : BaseViewModel<UiState, UiIntent, Effect>(
    UiState.initialUiState(preferences.getShownOnboarding())
) {
    //preferences.settings.getBoolean(Preferences.SHOW_ONBOARDING, true)
    private fun hideOnboarding() = scope.launch {
        //preferences.settings.putBoolean(Preferences.SHOW_ONBOARDING, false)
        preferences.setShownOnboarding()
        sendEffect(Effect.GoToAuthGraph)
    }


    override fun handleIntent(uiIntent: UiIntent) {
        when (uiIntent) {
            UiIntent.CompleteOnboarding -> hideOnboarding()
        }
    }

    override fun handleError(e: Throwable) {

    }
}