package com.baldomeronapoli.eventplanner.mvi

class ErrorManager {
    private val errors = mutableListOf<Throwable>()

    fun pushError(error: Throwable) {
        errors.add(error)
        // Aquí puedes agregar lógica adicional, como notificar a los observadores.
    }

    fun getErrors(): List<Throwable> {
        return errors
    }
}
