package com.baldomeronapoli.eventplanner.data.postgresql.dto

import com.baldomeronapoli.eventplanner.domain.models.Address
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDTO(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String?,

    @SerialName("street")
    val street: String,

    @SerialName("iso_country_code")
    val isoCountryCode: String?,

    @SerialName("country")
    val country: String,

    @SerialName("postal_code")
    val postalCode: String?,

    @SerialName("administrative_area")
    val administrativeArea: String?,

    @SerialName("sub_administrative_area")
    val subAdministrativeArea: String?,

    @SerialName("locality")
    val locality: String?,

    @SerialName("sub_locality")
    val subLocality: String?,

    @SerialName("thoroughfare")
    val thoroughfare: String?,

    @SerialName("sub_thoroughfare")
    val subThoroughfare: String?,

    @SerialName("latitude")
    val latitude: Double,

    @SerialName("longitude")
    val longitude: Double
) : Mappable<Address> {
    override fun map(): Address = Address(
        id = id,
        name = name,
        street = street,
        isoCountryCode = isoCountryCode,
        country = country,
        postalCode = postalCode,
        administrativeArea = administrativeArea,
        subAdministrativeArea = subAdministrativeArea,
        locality = locality,
        subLocality = subLocality,
        thoroughfare = thoroughfare,
        subThoroughfare = subThoroughfare,
        latitude = latitude,
        longitude = longitude
    )
}
