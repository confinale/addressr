package ch.confinale.address_formatter_kt.configuration

import ch.confinale.address_formatter_kt.utils.WW_ADDRESS_TEMPLATE
import ch.confinale.address_formatter_kt.utils.WW_DEFAULT
import ch.confinale.address_formatter_kt.utils.YAML_WORLWIDE
import org.yaml.snakeyaml.Yaml

/**
 * Implementation using OpenCageData mustache templates.
 */
class OpenCageDataConfiguration() : Configuration {

    private val settings: Map<String, Map<String, String>?>
    private val defaultTemplate: String

    init {
        val yaml = Yaml()
        val inputStream = this.javaClass.classLoader.getResourceAsStream(YAML_WORLWIDE)
        settings = yaml.load(inputStream)
        defaultTemplate = settings.get(WW_DEFAULT)!!.get(WW_ADDRESS_TEMPLATE) as String
    }

    override fun templateForCountry(countryCode: String): String {
        settings.get(countryCode).let { setting ->
            if (setting != null) {
                setting.get(WW_ADDRESS_TEMPLATE).let { template ->
                    return template!!
                }
            }
        }
        return defaultTemplate
    }
}
