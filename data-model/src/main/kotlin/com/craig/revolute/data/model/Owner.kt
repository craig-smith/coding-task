package com.craig.revolute.data.model

import javax.persistence.*

@Entity
data class Owner(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        val firstName: String?,

        val lastName: String?,

        val phoneNumber: String?,

        @OneToOne(targetEntity = Address::class, cascade = arrayOf(CascadeType.ALL))
        val address: Address?)