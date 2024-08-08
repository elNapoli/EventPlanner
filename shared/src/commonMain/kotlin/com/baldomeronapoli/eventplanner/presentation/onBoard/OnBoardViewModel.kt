package com.baldomeronapoli.eventplanner.presentation.onBoard

import com.baldomero.napoli.eventplanner.core.presentation.viewModel.BaseViewModel
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.Effect
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.UiState
import com.baldomeronapoli.eventplanner.utils.PreferencesManager
import com.rickclephas.kmp.observableviewmodel.launch

class OnBoardViewModel(
    private val preferences: PreferencesManager,

    ) : BaseViewModel<UiState, UiIntent, Effect>(
    UiState(preferences.getShownOnboarding())
) {
    private fun hideOnboarding() = scope.launch {
        preferences.setShownOnboarding()
        sendEffect(Effect.GoToAuthGraph)
    }


    override fun sendIntent(uiIntent: UiIntent) {
        when (uiIntent) {
            UiIntent.CompleteOnboarding -> hideOnboarding()
        }
    }

}