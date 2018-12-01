package com.craig.revolute.data.model.dto

import com.craig.revolute.data.model.Currency
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AccountDTOBuilderTest {

    @Test
    fun builderTest() {
        //given
        val address = AddressDTO.Builder()
                .id(1L)
                .country("country")
                .city("city")
                .street("street")
                .build()
        val owner = OwnerDTO.Builder()
                .id(1L)
                .address(address)
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("1234")
                .build()


        val expected = AccountDTO(1L, owner, hashMapOf(Currency.USD to 10.0, Currency.EUR to 10.0))

        //when
        val test = AccountDTO.Builder()
                .id(1L)
                .owner(owner)
                .money(Currency.USD, 10.0)
                .money(Currency.EUR, 10.0)
                .build()
        val test2 = AccountDTO.Builder()
                .id(1L)
                .owner(owner)
                .monies(mutableMapOf(Currency.USD to 10.0, Currency.EUR to 10.0))
                .build()

        //then
        assertThat(expected).isEqualTo(test)
        assertThat(expected).isEqualTo(test2)
    }
}