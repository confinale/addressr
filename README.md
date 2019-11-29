# addressr

addressr is a kotlin library for formatting addresses using worldwide templates from [OpenCageData](https://github.com/OpenCageData/address-formatting).

## Quick example
Just pass an address object and you get a formatted address based on locale convenience

```kotlin
// initialize formatter instance
val formatter = AddressFormatter(OpenCageDataConfiguration())

// format the address using entity
val formattedAddress = formatter.formatLocale(address)
```

## Configuration

You can use the default configuration, which is ```OpenCageDataConfiguration.kt```. You can also provide your own config
implementing the ```Configuration.kt``` interface.

## Further reading

We use the templates defined in ```worldwide.yaml```. In case there is an unknonw country code passed to the formatter, 
the default template is used (in that case ```generic1``` in ```workdwide.yaml```).

So far there is no support for the following features, which exist in ```wordwide.yaml```:
* use_country
* change_country
* replace
* postformat_replace
* fallback_template
* add_component

Feel free to make PRs.
