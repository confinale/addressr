package ch.confinale.address_formatter_kt.formatter

import ch.confinale.address_formatter_kt.configuration.OpenCageDataConfiguration
import ch.confinale.address_formatter_kt.entities.Address
import com.github.mustachejava.DefaultMustacheFactory
import java.io.StringReader
import java.io.StringWriter


class AddressFormatter(
    private val configuration: OpenCageDataConfiguration
) {

    /**
     * Pass the Address instance and create a string representation based on the country.
     *
     * @param address The address instance.
     *
     * @return A string representation of the given address instance.
     */
    fun format(address: Address): String {
        val template = configuration.templateForCountry(address.country_code)
        val mustacheFactory = DefaultMustacheFactory()
        val mustache = mustacheFactory.compile(StringReader(template), address.country_code)
        val filledTemplate = StringWriter()
        mustache.execute(filledTemplate, address).flush()

        val result = cleanAddress(filledTemplate.toString())

        return result
    }

    /**
     * Removes whitespace and empty lines in address representation.
     *
     * @param addressRepresentation The string representation of the address.
     *
     * @return The cleanup address.
     */
    private fun cleanAddress(addressRepresentation: String): String {
        return addressRepresentation.lines()
            .filter { line -> !line.isBlank() }
            .map { line -> line.trim() }
            .joinToString("\n")
    }
}
