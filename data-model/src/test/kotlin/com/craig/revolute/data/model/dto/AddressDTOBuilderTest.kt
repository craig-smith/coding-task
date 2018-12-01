package com.craig.revolute.data.model.dto

class AddressDTOBuilderTest {

    @Test
    fun builderTest() {
        //given
        val expected = AddressDTO("country", "city", "street")

        //when
        val test = AddressDTO.Builder()
                .country("country")
                .city("city")
                .street("street")
                .build()

        //then
        assertThat(expected).isEqualTo(test)
    }
}