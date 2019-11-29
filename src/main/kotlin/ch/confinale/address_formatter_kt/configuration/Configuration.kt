package ch.confinale.address_formatter_kt.configuration

/**
 * A configuration interface for initializing the template data. An instance of Configuration is the source for
 * accessing templates.
 */
interface Configuration {

    /**
     * Return the mustache template for a given country. Return the fallback template if no country specfic template
     * was found.
     *
     * @param countryCode The country code, e.g. DE, GB, ES, etc.
     *
     * @return The template for the given country, falllbackTemplate otherwise.
     */
    fun templateForCountry(countryCode: String): String
}