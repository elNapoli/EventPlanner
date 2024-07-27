package com.baldomeronapoli.eventplanner.domain.models

data class NPlace(
    val coordinates: NCoordinates = NCoordinates(),
    val name: String? = null,
    val street: String? = null,
    val isoCountryCode: String? = null,
    val country: String? = null,
    val postalCode: String? = null,
    val administrativeArea: String? = null,
    val subAdministrativeArea: String? = null,
    val locality: String? = null,
    val subLocality: String? = null,
    val thoroughfare: String? = null,
    val subThoroughfare: String? = null,
)