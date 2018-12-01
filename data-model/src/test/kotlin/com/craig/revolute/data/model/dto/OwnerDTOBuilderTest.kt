package com.craig.revolute.data.model.dto

class OwnerDTOBuilderTest {

    @Test
    fun builderTest() {
        //given
        val address = AddressDTO.Builder()
                .city("city")
                .country("country")
                .street("street")
                .build()
        val expected = OwnerDTO(1, "firstName", "lastName", "1234", address)

        //when
        val test = OwnerDTO.Builder()
                .id(1)
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("1234")
                .address(address)
                .build()

        //then
        assertThat(expected).isEqualTo(test)
    }
}