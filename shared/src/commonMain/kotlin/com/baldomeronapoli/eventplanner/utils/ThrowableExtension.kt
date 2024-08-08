package com.baldomeronapoli.eventplanner.utils

import co.touchlab.kermit.Logger
import com.baldomero.napoli.common.network.MyError

import io.github.jan.supabase.exceptions.RestException


fun Throwable.toMyError(): MyError {
    Logger.e("explot algo, solucionar o moriremos $this")
    val errorMessage = when (this) {
        is RestException -> this.description
            ?: "Error desconocido AuthWeakPasswordException"

        else -> "Error desconocido: $this ${this.message}"
    }
    return MyError(message = errorMessage)
}
