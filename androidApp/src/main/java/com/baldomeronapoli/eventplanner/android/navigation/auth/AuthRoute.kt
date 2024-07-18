package com.baldomeronapoli.eventplanner.android.navigation.auth

import com.baldomeronapoli.eventplanner.android.navigation.route.Route

sealed class AuthRoute(override val path: String, override val title: String) : Route(path, title) {
    data object SignIn : AuthRoute("auth-sign-in", "Iniciar sesion")
    data object SignUp : AuthRoute("auth-sign-up", "Registrarse")
    data object ValidateEmail : AuthRoute("auth-validate-email", "Validar email")
    data object SuccessfulSignUp : AuthRoute("auth-successful-sign-up", "Éxito al registrarse")
}