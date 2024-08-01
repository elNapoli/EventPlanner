package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.firebaseModels.FAddress
import com.baldomeronapoli.eventplanner.mappers.Mappable
import com.baldomeronapoli.eventplanner.utils.randomUUID
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    override val id: String = randomUUID,
    val coordinates: NCoordinates = NCoordinates(0.0, 0.0),
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
) : BaseModel, Mappable<FAddress> {
    override fun map(): FAddress = FAddress(
        id = id,
        name = name!!,
        street = street!!,
        isoCountryCode = isoCountryCode!!,
        country = country!!,
        postalCode = postalCode,
        administrativeArea = administrativeArea,
        subAdministrativeArea = subAdministrativeArea,
        locality = locality!!,
        subLocality = subLocality,
        thoroughfare = thoroughfare,
        subThoroughfare = subThoroughfare,
        latitude = coordinates.latitude,
        longitude = coordinates.longitude,
    )
}