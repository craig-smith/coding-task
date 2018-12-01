package com.craig.revolute.data.model.dto

import com.craig.revolute.data.model.Currency

data class AccountDTO(
        val owner: OwnerDTO?,
        val monies: Map<Currency, Double>?) {

    data class Builder(
            private var owner: OwnerDTO? = null,
            private var monies: MutableMap<Currency, Double> = mutableMapOf()
    ) {
        fun owner(owner: OwnerDTO) = apply { this.owner = owner }
        fun monies(monies: MutableMap<Currency, Double>) = apply { this.monies = monies }
        fun money(currency: Currency, amount: Double) = apply { this.monies.put(currency, amount) }
        fun build() = AccountDTO(owner, monies.toMap())
    }
}