package com.craig.revolute.data.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull

@ApiModel(value = "Owner", description = "The owner of the account")
data class OwnerDTO(

        val id: Long?,

        @ApiModelProperty(example = "Craig", value = "Person's first name", required = true)
        @field:NotNull
        val firstName: String?,

        @ApiModelProperty(example = "Smith", value = "Person's last name", required = true)
        @field:NotNull
        val lastName: String?,

        @ApiModelProperty(example = "555-1234", value = "phone number", required = true)
        @field:NotNull
        val phoneNumber: String?,

        @field:NotNull
        val address: AddressDTO?) {
    data class Builder(
            private var id: Long? = null,
            private var firstName: String? = null,
            private var lastName: String? = null,
            private var phoneNumber: String? = null,
            private var address: AddressDTO? = null) {
        fun id(id: Long) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun phoneNumber(phoneNumber: String) = apply { this.phoneNumber = phoneNumber }
        fun address(address: AddressDTO) = apply { this.address = address }
        fun build() = OwnerDTO(id, firstName, lastName, phoneNumber, address)
    }


}