package ch.confinale.address_formatter_kt.entities

import java.util.function.Function

data class Address(
    var country: String,
    var country_code: String,
    var house_number: String,
    var road: String,
    var postcode: String,
    var city: String,

    val house: String? = null,
    var attention: String? = null,
    var suburb: String? = null,
    var city_district: String? = null,
    var neighbourhood: String? = null,
    var town: String? = null,
    val state_district: String? = null,
    val state_code: String? = null
) {

    // used by mustache template in worldwide.yaml
    fun first(): Function<Any, Any>? {
        return Function { obj: Any ->
            (obj as String).split("||")
                .stream()
                .filter { entry -> !entry.isBlank() }
                .map { entry -> entry.trim() } // remove blanks in the entries, cause there is always whitespace in the mustache template between the keys
                .findFirst()
                .orElse(null)
        }
    }

}