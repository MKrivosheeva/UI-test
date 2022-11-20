package data.countriesAndCities;

public enum CountriesData {

    RUSSIA("Россия"),
    MOLDOVA("Молдова");

    private String name;
    CountriesData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
