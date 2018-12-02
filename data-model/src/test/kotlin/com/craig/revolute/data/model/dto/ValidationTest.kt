package com.craig.revolute.data.model.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import javax.validation.Validation
import javax.validation.Validator

class ValidationTest {

    val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun ownerDTOShouldBeValid() {
        //given
        val validOwner = validOwner

        //when
        val constraintViolations = validator.validate(validOwner)

        //then
        assertThat(constraintViolations.size).isEqualTo(0)
    }

    @Test
    fun addressDTOShouldBeValid() {
        //given
        val validAddres = validAddress()

        //when
        val constraintViolations = validator.validate(validAddres)

        //then
        assertThat(constraintViolations.size).isEqualTo(0)
    }

    @Test
    fun accountShouldBeValid() {
        //given
        val validAccount = validAccount

        //when
        val constraintViolations = validator.validate(validAccount)

        //then
        assertThat(constraintViolations.size).isEqualTo(0)
    }

    @Test
    fun ownerShouldNotBeValid() {
        //given
        val invalidOwner = invalidOwner.invoke()

        //when
        val validationConstraints = validator.validate(invalidOwner)

        //then
        assertThat(invalidOwner.firstName).isNull()
        assertThat(validationConstraints.size).isEqualTo(1)
    }

    @Test
    fun addressShouldNotBeValid() {
        //given
        val invalidAddress = invalidAddress.invoke()

        //when
        val validationConstraints = validator.validate(invalidAddress)

        //then
        assertThat(invalidAddress.city).isNull()
        assertThat(validationConstraints.size).isEqualTo(1)
    }

    @Test
    fun accountShouldNotBeValid() {
        //given
        val invalidAccount = invalidAccount.invoke()

        //when
        val constraintViolations = validator.validate(invalidAccount)

        //then
        assertThat(invalidAccount.owner).isNull()
        assertThat(constraintViolations.size).isEqualTo(1)
    }

    val validAccount = {
        AccountDTO.Builder()
                .owner(validOwner.invoke())
                .build()
    }


    val validOwner = {
        OwnerDTO.Builder()
                .id(0L)
                .firstName("firstname")
                .lastName("lastname")
                .phoneNumber("1234")
                .address(validAddress.invoke())
                .build()
    }

    val validAddress = {
        AddressDTO.Builder()
                .city("city")
                .country("country")
                .street("street")
                .build()
    }

    val invalidOwner = {
        OwnerDTO.Builder()
                .id(0L)
                .lastName("lastname")
                .phoneNumber("1234")
                .address(validAddress.invoke())
                .build()
    }

    val invalidAddress = {
        AddressDTO.Builder()
                .country("country")
                .street("street")
                .build()
    }

    val invalidAccount = {
        AccountDTO.Builder()
                .build()
    }
}