package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.postgresql.dto.AddressDTO
import com.baldomeronapoli.eventplanner.mappers.BiMappable
import com.baldomeronapoli.eventplanner.presentation.models.AddressUI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
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
) : BiMappable<AddressDTO, AddressUI> {

    override fun mapToDto(): AddressDTO {
        return AddressDTO(
            id = this.id,
            name = this.name,
            street = this.street,
            isoCountryCode = this.isoCountryCode,
            country = this.country,
            postalCode = this.postalCode,
            administrativeArea = this.administrativeArea,
            subAdministrativeArea = this.subAdministrativeArea,
            locality = this.locality,
            subLocality = this.subLocality,
            thoroughfare = this.thoroughfare,
            subThoroughfare = this.subThoroughfare,
            latitude = this.latitude,
            longitude = this.longitude
        )
    }

    override fun mapToUI(): AddressUI {
        return AddressUI(
            id = this.id,
            name = this.name,
            street = this.street,
            isoCountryCode = this.isoCountryCode,
            country = this.country,
            postalCode = this.postalCode,
            administrativeArea = this.administrativeArea,
            subAdministrativeArea = this.subAdministrativeArea,
            locality = this.locality,
            subLocality = this.subLocality,
            thoroughfare = this.thoroughfare,
            subThoroughfare = this.subThoroughfare,
            latitude = this.latitude,
            longitude = this.longitude
        )
    }
}
