package com.craig.revolute.data.model.dto

data class AddressDTO(
        val country: String?,
        val city: String?,
        val street: String?) {
    data class Builder(
            private var country: String? = null,
            private var city: String? = null,
            private var street: String? = null) {
        fun country(country: String) = apply { this.country = country }
        fun city(city: String) = apply { this.city = city }
        fun street(street: String) = apply { this.street = street }
        fun build() = AddressDTO(country, city, street)

    }
}