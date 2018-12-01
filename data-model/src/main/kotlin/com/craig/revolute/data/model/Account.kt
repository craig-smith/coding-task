package com.craig.revolute.data.model

import javax.persistence.*

@Entity
data class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @OneToOne(targetEntity = Owner::class, cascade = arrayOf(CascadeType.ALL))
        val owner: Owner?,

        @ElementCollection(fetch = FetchType.EAGER)
        val monies: Map<Currency, Double>?) //double here could be changed for something better....