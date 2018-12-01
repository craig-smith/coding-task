package com.craig.revolute.data.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Address", description = "The address of the owner")
data class AddressDTO(
        var id: Long?,
        @ApiModelProperty(example = "Poland", value = "Country where the person lives", required = true)
        val country: String?,

        @ApiModelProperty(example = "Krakow", value = "City where the person lives", required = true)
        val city: String?,

        @ApiModelProperty(example = "Some Street", value = "Street where the person lives", required = true)
        val street: String?) {
    data class Builder(
            private var id: Long? = null,
            private var country: String? = null,
            private var city: String? = null,
            private var street: String? = null) {
        fun id(id: Long?) = apply { this.id = id }
        fun country(country: String) = apply { this.country = country }
        fun city(city: String) = apply { this.city = city }
        fun street(street: String) = apply { this.street = street }
        fun build() = AddressDTO(id, country, city, street)

    }
}