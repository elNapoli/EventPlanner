package com.baldomeronapoli.eventplanner.presentation.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressUI(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("name")
    val name: String? = null,

    @SerialName("street")
    val street: String = "",

    @SerialName("iso_country_code")
    val isoCountryCode: String? = null,

    @SerialName("country")
    val country: String = "",

    @SerialName("postal_code")
    val postalCode: String? = null,

    @SerialName("administrative_area")
    val administrativeArea: String? = null,

    @SerialName("sub_administrative_area")
    val subAdministrativeArea: String? = null,

    @SerialName("locality")
    val locality: String? = null,

    @SerialName("sub_locality")
    val subLocality: String? = null,

    @SerialName("thoroughfare")
    val thoroughfare: String? = null,

    @SerialName("sub_thoroughfare")
    val subThoroughfare: String? = null,

    @SerialName("latitude")
    val latitude: Double = 0.0,

    @SerialName("longitude")
    val longitude: Double = 0.0
)