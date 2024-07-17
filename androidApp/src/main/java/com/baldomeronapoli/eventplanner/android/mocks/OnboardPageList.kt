package com.baldomeronapoli.eventplanner.android.mocks

import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.models.OnboardPage

val onboardPagesList = listOf(
    OnboardPage(
        imageRes = R.drawable.onboard_page_1,
        title = "¡Bienvenido!",
        description = "NEvent llegó para revolucionar la forma de organizar eventos. ¡Si trabajas en la organización de eventos, NEvent es perfecto para ti!",
    ), OnboardPage(
        imageRes = R.drawable.onboard_page_2,
        title = "Encuentra un evento interesante cerca de ti",
        description = "Adquiere más experiencia participando en talleres y otros eventos interesantes cerca de ti."
    ), OnboardPage(
        imageRes = R.drawable.onboard_page_3,
        title = "Recibir notificaciones s obre tu evento",
        description = "No te compliques, te recordaremos tu próximo evento."
    )
)