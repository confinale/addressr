# addressr

addressr is a kotlin library for formatting addresses using worldwide templates from [OpenCageData](https://github.com/OpenCageData/address-formatting).

## Installation

Using gradle:
```
dependencies {
    implementation 'ch.confinale:addressr:1.1.0'
}
```

Using maven:
```
<dependency>
  <groupId>ch.confinale</groupId>
  <artifactId>addressr</artifactId>
  <version>1.1.0</version>
</dependency>
```

## Quick example
Just pass an address object and you get a formatted address based on locale convenience

```kotlin
// initialize formatter instance
val formatter = AddressFormatter(OpenCageDataConfiguration())
val address = Address(
                  house = "House",
                  road = "Road",
                  house_number = "1",
                  postcode = "1234",
                  city = "Vaduz",
                  country = "Liechtenstein",
                  country_code = "LI"
              )

// format the address using entity
val formattedAddress = formatter.format(address)


// result :
House
Road 1
1234 Vaduz
Liechtenstein
```

## Configuration

You can use the default configuration, which is ```OpenCageDataConfiguration.kt```. You can also provide your own config
implementing the ```Configuration.kt``` interface.

## Further reading

We use the templates defined in ```worldwide.yaml```. In case there is an unknonw country code passed to the formatter, 
the default template is used (in that case ```generic1``` in ```workdwide.yaml```).

So far there is no support for the following features, which exist in ```wordwide.yaml```:
* change_country
* replace
* postformat_replace
* fallback_template
* add_component

Feel free to make PRs.
