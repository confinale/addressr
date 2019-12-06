package ch.confinale.address_formatter_kt.formatter

import Helpers.KotlinParameterizedTest
import ch.confinale.address_formatter_kt.configuration.OpenCageDataConfiguration
import ch.confinale.address_formatter_kt.entities.Address
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@KotlinParameterizedTest
internal class AddressFormatterTest {

    val formatter = AddressFormatter(OpenCageDataConfiguration())

    fun addresses(): Stream<Arguments> =
        Stream.of(
            Arguments.of(
                "Test DE (generic1) template",
                Address(country_code = "DE", country = "Deutschland", road = "Schillerstraße", house_number = "36", postcode = "80336", city = "München", house = "Max Muster"),
                "Max Muster\n" +
                        "Schillerstraße 36\n" +
                        "80336 München\n" +
                        "Deutschland"
            ),
            Arguments.of(
                "Test GB(generic2) template",
                Address(
                    house = "Peter Watson",
                    road = "Bigstreet",
                    house_number = "69",
                    postcode = "80336",
                    city = "London",
                    country = "Great Britain",
                    country_code = "GB"
                ),
                "Peter Watson\n" +
                        "69 Bigstreet\n" +
                        "London 80336\n" +
                        "Great Britain"
            ),
            Arguments.of(
                "Test LU (generic3) template",
                Address(
                    house = "Le bureau d'imposition Luxembourg 2 (voir aussi compétences de RTS 2)",
                    road = "rue de Hollerich",
                    house_number = "111",
                    postcode = "1741",
                    city = "Luxembourg",
                    country = "Luxembourg",
                    country_code = "LU"
                ),
                "Le bureau d'imposition Luxembourg 2 (voir aussi compétences de RTS 2)\n" +
                        "111 rue de Hollerich\n" +
                        "1741 Luxembourg\n" +
                        "Luxembourg"
            ),
            Arguments.of(
                "Test US (generic4) template",
                Address(house = "USA AG", road = "Route", house_number = "66", postcode = "1000", city = "New York", country = "USA", country_code = "US"),
                "USA AG\n" +
                        "66 Route\n" +
                        "New York,  1000\n" +
                        "USA"
            ),
            Arguments.of(
                "Test JP template",
                Address(house = "Taro Tanaka", road = "Kotobukicho", house_number = "10-23", postcode = "951-8073", city = "Nigata", country_code = "JP", country = "Japan"),
                "Taro Tanaka\n" +
                        "10-23 Kotobukicho\n" +
                        "Nigata,  951-8073\n" +
                        "Japan"
            ),
            Arguments.of(
                "Test default template using an invalid country code",
                Address(
                    house = "House",
                    road = "Road",
                    house_number = "12345678",
                    postcode = "987654",
                    city = "City",
                    country_code = "ABCDEFEDED",
                    country = "INVALID COUNTRY"
                ),
                "House\n" +
                        "Road 12345678\n" +
                        "987654 City\n" +
                        "INVALID COUNTRY"
            )
        )


    @ParameterizedTest
    @MethodSource("addresses")
    fun `format valid address`(description: String, address: Address, expectedRepresentation: String) {
        println("****************************")
        println(description)
        println("****************************")
        println()

        val result = formatter.format(address)
        println(result)

        assertEquals(expectedRepresentation, result)

        println()
        println("############################")
    }


}
