package com.baldomeronapoli.eventplanner.utils

import co.touchlab.kermit.Logger
import com.baldomero.napoli.common.network.MyError
import io.github.jan.supabase.exceptions.RestException

sealed class NetworkResult<out T>(
    open val data: T? = null,
    open val message: String?,
    open val error: MyError? = null
) {

    data class Success<out T>(
        override val data: T,
        override val message: String = "Petición finalizada con éxito"
    ) :
        NetworkResult<T>(data = data, message = null)

    data class Error<out T>(override val error: MyError) :
        NetworkResult<T>(error = error, message = error.message)

    data class Loading<out T>(val isLoading: Boolean) :
        NetworkResult<T>(data = null, message = null, error = null)
}


fun Throwable.toMyError(): MyError {
    Logger.e("explot algo, solucionar o moriremos $this")
    val errorMessage = when (this) {
        is RestException -> this.description
            ?: "Error desconocido AuthWeakPasswordException"

        else -> "Error desconocido: $this ${this.message}"
    }
    return MyError(message = errorMessage)
}
