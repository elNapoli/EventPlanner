package com.baldomeronapoli.eventplanner.mvi

class LoadingManager {
    private var isLoading: Boolean = false

    fun setLoading(loading: Boolean) {
        isLoading = loading
        // Aquí puedes agregar lógica adicional, como notificar a los observadores.
    }

    fun isLoading(): Boolean {
        return isLoading
    }
}
