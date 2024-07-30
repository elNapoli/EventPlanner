package com.baldomeronapoli.eventplanner.data.firebaseModels

import com.baldomeronapoli.eventplanner.domain.models.Address
import com.baldomeronapoli.eventplanner.domain.models.NCoordinates
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.Serializable

@Serializable
data class FAddress(
    val id: String = "",
    val name: String = "",
    val street: String = "",
    val isoCountryCode: String = "",
    val country: String = "",
    val postalCode: String? = null,
    val administrativeArea: String? = null,
    val subAdministrativeArea: String? = null,
    val locality: String = "",
    val subLocality: String? = null,
    val thoroughfare: String? = null,
    val subThoroughfare: String? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
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
        coordinates = NCoordinates(latitude, longitude)
    )
}