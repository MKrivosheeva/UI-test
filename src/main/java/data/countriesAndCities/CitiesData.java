package data.countriesAndCities;
public enum CitiesData  {

    MOSCOW("Москва", CountriesData.RUSSIA),
    PITER("Санкт-Петербург", CountriesData.RUSSIA),
    ANAPA("Анапа", CountriesData.RUSSIA),
    BELCI("Бельцы", CountriesData.MOLDOVA);

    private String name;
    private CountriesData countriesData;

    CitiesData(String name, CountriesData countriesData) {
        this.name = name;
        this.countriesData = countriesData;
    }

    public String getName() {
        return name;
    }

    public CountriesData getCountriesData() {

        return countriesData;
    }
}

