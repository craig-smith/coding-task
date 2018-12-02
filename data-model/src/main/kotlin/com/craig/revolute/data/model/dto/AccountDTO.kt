package com.craig.revolute.data.model.dto

import com.craig.revolute.data.model.Currency
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull

@ApiModel
data class AccountDTO(
        val id: Long?,

        @ApiModelProperty(value = "Owner of the account", required = true)
        @field:NotNull
        val owner: OwnerDTO?,

        @ApiModelProperty(example = "EUR: 1000", value = "Currencies and Values", required = false)
        val monies: Map<Currency, Double>?) {

    data class Builder(
            private var id: Long? = null,
            private var owner: OwnerDTO? = null,
            private var monies: MutableMap<Currency, Double> = mutableMapOf()
    ) {
        fun id(id: Long) = apply { this.id = id }
        fun owner(owner: OwnerDTO) = apply { this.owner = owner }
        fun monies(monies: MutableMap<Currency, Double>) = apply { this.monies = monies }
        fun money(currency: Currency, amount: Double) = apply { this.monies.put(currency, amount) }
        fun build() = AccountDTO(id, owner, monies.toMap())
    }
}