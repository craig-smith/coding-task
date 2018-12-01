package com.craig.revolute.data.model

enum class Currency(val symbol: String, val printedName: String) {
    USD("U+0024", "US Dollar"),
    EUR("U+20AC", "Euro"),
    JPY("U+00A5", "Japanese Yen"),
    GBP("U+00A3", "Pound Sterling"),
    PLN("U+0142", "Polish złoty")
}