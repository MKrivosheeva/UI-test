package data.countriesAndCities;
public enum CitiesData  {

    Moscow("Москва", CountriesData.Russia),
    Piter("Санкт-Петербург", CountriesData.Russia),
    Anapa("Анапа", CountriesData.Russia),
    BELCI("Лимассол", CountriesData.MOLDOVA);

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

    //метод принимает CitiesData и выбирает страну и город
    //в форме метод принимает параметр CitiesData. 1. выбор страны getCountriesData() - возврат значения из енама по геттеру
    //2. поиск город. Город брать из citisies
    //рандомно не надо.передавать в тесте
    //формат работы тоже енам с чекбоксами и булен тру/фолз
    //надо проверять первоначальное состояние. клик только в том состоянии, которое не нужно
    //метод принимает да/нет (один параметр булевский)
}

