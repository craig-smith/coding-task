package com.craig.revolute.data.model.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AddressDTOBuilderTest {

    @Test
    fun builderTest() {
        //given
        val expected = AddressDTO(1L, "country", "city", "street")

        //when
        val test = AddressDTO.Builder()
                .id(1L)
                .country("country")
                .city("city")
                .street("street")
                .build()

        //then
        assertThat(expected).isEqualTo(test)
    }
}