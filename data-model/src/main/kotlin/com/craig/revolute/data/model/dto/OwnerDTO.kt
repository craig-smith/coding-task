package com.craig.revolute.data.model.dto

data class OwnerDTO(

        val id: Int?,
        val firstName: String?,
        val lastName: String?,
        val phoneNumber: String?,
        val address: AddressDTO?) {
    data class Builder(
            private var id: Int? = null,
            private var firstName: String? = null,
            private var lastName: String? = null,
            private var phoneNumber: String? = null,
            private var address: AddressDTO? = null) {
        fun id(id: Int) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun phoneNumber(phoneNumber: String) = apply { this.phoneNumber = phoneNumber }
        fun address(address: AddressDTO) = apply { this.address = address }
        fun build() = OwnerDTO(id, firstName, lastName, phoneNumber, address)
    }


}