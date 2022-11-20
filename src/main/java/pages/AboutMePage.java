package pages;

import components.InputFormComponent;
import data.*;
import data.countriesAndCities.CitiesData;
import data.countriesAndCities.CountriesData;
import data.textFieldsLocators.InputFieldData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AboutMePage extends AbsBasePage {

    public AboutMePage(WebDriver driver) {
        super(driver);
    }

    private String userRuFirstName = ruFaker.name().firstName();
    private String userRuLastName = ruFaker.name().lastName();
    private String userEnFirstName = enFaker.name().firstName();
    private String userEnLastName = enFaker.name().lastName();
    private String userEnBlogName = enFaker.name().firstName();
    private Date userBirthDate = dateFaker.date().birthday(18, 105);
    private String userCompany = ruFaker.company().name();
    private String userPosition = ruFaker.company().profession();
    private String userNetworkUserNameFirst = enFaker.name().username();
    private String userNetworkUserNameSecond = enFaker.name().username();

    private String userCountry = CountriesData.MOLDOVA.getName();
    private String userCity = CitiesData.BELCI.getName();
    private String userEnglishLevel = EnglishLevelData.ADVANCED.getName();
    private Boolean userReadyToRelocate = ReadyToRelocateData.TRUE.getValue();
    private Boolean workFormatFlexible = false;
    private Boolean workFormatRemote = true;
    private Boolean workFormatFullTime = false;

    private String userGender = GenderData.FEMALE.getName();

    private DevLanguagesData userDevLanguageFromEnum = DevLanguagesData.RUBY;
    private DevExperienceData userDevExperienceFromEnum = DevExperienceData.TWOYEARS;

    private String userNetworkFirst = SocialNetworksData.FACEBOOK.getName();
    private String userNetworkSecond = SocialNetworksData.TELEGRAM.getName();

    private String addAvatarSelector = "input[type='file']";
    private String imagePath = "/src/main/java/data/images/avatar.jpg";
    private String modalConfirmation = "[class='modal settings-photo-modal'] button";

    private String fullTimeString = "input[title='Полный день']";
    private String partTimeString = "input[title='Гибкий график']";
    private String remoteString = "input[title='Удаленно']";

    private String countrySelector = "button[title='" + userCountry + "']";
    private String citySelector = "button[title='" + userCity + "']";
    private String englishLevelSelector = "button[title='" + userEnglishLevel + "']";
    private String readyToRelocateTrue = "input[type='radio'][value='True']";
    private String readyToRelocateFalse = "input[type='radio'][value='False']";

    @FindBy(css = "input[title='День рождения']")
    WebElement birthDateField;

    @FindBy(css = "input[name='country']+ div")
    WebElement countryField;

    @FindBy(css = "input[data-title='Город']+div")
    WebElement cityField;

    @FindBy(css = "input[data-title='Уровень знания английского языка']+div")
    WebElement englishLevelField;

    @FindBy(css = "button[class$='lk-cv-custom-select-add']")
    WebElement addNetworkButton;

    @FindBy(css = "select[name='gender']")
    WebElement genderField;

    @FindBy(css = "a[class^='experience-add']")
    WebElement addExperienceButton;

    @FindBy(css = "select[name='experience-0-experience']")
    WebElement devLanguageSelector;

    @FindBy(css = "select[name='experience-0-level']")
    WebElement devExperienceSelector;

    @FindBy(xpath = "//input[@name='contact-0-service']/..")
    WebElement networkFirstTypeSelectWebelement;

    @FindBy(css = "input[name='contact-0-value']")
    WebElement networkFirstValueField;

    @FindBy(xpath = "//input[@name='contact-1-service']/..")
    WebElement networkSecondTypeSelectorWebelement;

    @FindBy(css = "input[name='contact-1-value']")
    WebElement networkSecondValueField;

    @FindBy(css = "button[title='Сохранить и продолжить']")
    WebElement saveAndContinueButton;

    private By networkBlockLocator = By.xpath("//div[@class='container__row js-formset-row']");
    private By exeperienceBlockLocator = By.cssSelector("[class='experience-row js-formset-row']");
    private InputFormComponent inputForm = new InputFormComponent(driver);
    private Select select = null;

    public AboutMePage fillPersonalData() {

        inputForm.loadImage(addAvatarSelector, imagePath, modalConfirmation);
        inputForm.fillInputFields(InputFieldData.FIRSTNAMERU, userRuFirstName);
        inputForm.fillInputFields(InputFieldData.LASTNAMERU, userRuLastName);
        inputForm.fillInputFields(InputFieldData.FIRSTNAMEEN, userEnFirstName);
        inputForm.fillInputFields(InputFieldData.LASTNAMEEN, userEnLastName);
        inputForm.fillInputFields(InputFieldData.BLOGNAME, userEnBlogName);
        inputForm.fillTheDateField(birthDateField, userBirthDate);
        logger.info("заполнен блок 'Персональные данные'");
        return this;
    }

    public AboutMePage fillGeneralInfo() {
        By countryListSelector = By.xpath("//input[@name='country']/../following-sibling::div");
        inputForm.popupInteraction(countryField, countryListSelector, countrySelector);
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(cityField, "value disabled", "disabled")));
        By cityListSelector = By.xpath("//input[@name='city']/../following-sibling::div");
        inputForm.popupInteraction(cityField, cityListSelector, citySelector);
        By englishLevelListSelector = By.xpath("//input[@name='english_level']/../following-sibling::div");
        inputForm.popupInteraction(englishLevelField, englishLevelListSelector, englishLevelSelector);
        inputForm.radioButtonInteraction(readyToRelocateFalse, readyToRelocateTrue, userReadyToRelocate);
        inputForm.checkboxInteraction(fullTimeString, workFormatFullTime);
        inputForm.checkboxInteraction(partTimeString, workFormatFlexible);
        inputForm.checkboxInteraction(remoteString, workFormatRemote);
        logger.info("заполнен блок 'Основная информация'");
        return this;

    }

    public AboutMePage fillContactInfo() {
        By deleteLocator = By.xpath("//div[@class='container__row js-formset-row']//div[3]//button[contains(@class, 'delete')]");
        List<WebElement> delButtons = driver.findElements(deleteLocator);
        switch (delButtons.size()) {
            case 0: {
                addNetworkButton.click();
                addNetworkButton.click();
                break;
            }

            case 1: {
                addNetworkButton.click();
                break;
            }
            default: {
                for (int i = (driver.findElements(networkBlockLocator).size()); i > 2; i--) {
                    delButtons.get(i - 1).click();
                }
                break;
            }
        }
        By list = By.cssSelector("div[class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']");
        networkFirstTypeSelectWebelement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(list));
        String userFirstNetworkTypeSelector = String.format("//div[@data-num='0']//button[@data-value='%s']", userNetworkFirst);
        driver.findElement(By.xpath(userFirstNetworkTypeSelector)).click();
        networkFirstValueField.clear();
        networkFirstValueField.sendKeys(userNetworkUserNameFirst);
        String userSecondNetworkTypeSelector = String.format("//div[@data-num='1']//button[@data-value='%s']", userNetworkSecond);
        networkSecondTypeSelectorWebelement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(list));
        driver.findElement(By.xpath(userSecondNetworkTypeSelector)).click();
        networkSecondValueField.clear();
        networkSecondValueField.sendKeys(userNetworkUserNameSecond);
        logger.info("Заполнен блок 'Контактная информация'");
        return this;
    }

    public AboutMePage fillOtherInfo() {
        select = new Select(genderField);
        if (userGender.equalsIgnoreCase(GenderData.MALE.getName())) {
            select.selectByValue(GenderData.MALE.getOption());
        } else {
            select.selectByValue(GenderData.FEMALE.getOption());
        }
        inputForm.fillInputFields(InputFieldData.COMPANY, userCompany);
        inputForm.fillInputFields(InputFieldData.POSITION, userPosition);
        logger.info("заполнен блок 'Другое'");
        return this;
    }

    public AboutMePage fillDevInfo() {
        By deleteLocator = By.cssSelector("[class^='experience-row__remove']");
        List<WebElement> delButtons = driver.findElements(deleteLocator);
        if (driver.findElements(exeperienceBlockLocator).size() > 1) {
            for (int i = (driver.findElements(exeperienceBlockLocator).size()); i > 1; i--) {
                delButtons.get(i - 1).click();
            }
        }
        if (driver.findElements(exeperienceBlockLocator).size() == 0) {
            addExperienceButton.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(exeperienceBlockLocator));
        }
        select = new Select(devLanguageSelector);
        select.selectByValue(userDevLanguageFromEnum.getOption());
        select = new Select(devExperienceSelector);
        select.selectByValue(userDevExperienceFromEnum.getOption());
        logger.info("заполнен блок 'Опыт разработки'");
        return this;
    }

    public AboutMePage saveAll() {
        saveAndContinueButton.click();
        wait.until(ExpectedConditions.urlContains("skills"));
        logger.info("данные на странице сохранены");
        return this;
    }

    public AboutMePage assertPersonalInfo() {
        inputForm.assertInputFields(InputFieldData.FIRSTNAMERU, userRuFirstName);
        inputForm.assertInputFields(InputFieldData.LASTNAMERU, userRuLastName);
        inputForm.assertInputFields(InputFieldData.FIRSTNAMEEN, userEnFirstName);
        inputForm.assertInputFields(InputFieldData.LASTNAMEEN, userEnLastName);
        inputForm.assertInputFields(InputFieldData.BLOGNAME, userEnBlogName);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(userBirthDate);
        Assertions.assertEquals(date, birthDateField.getAttribute("value"), "Дата рождения сохранена некорректно");
        Assertions.assertFalse(driver.findElement(By.cssSelector("[class^='settings-photo__photo']")).getAttribute("style").isEmpty(), "Изображение не загружено");
        logger.info("Персональные данные сохранены корректно");
        return this;
    }

    public AboutMePage assertGeneralInfo() {
        Assertions.assertEquals(userCountry, countryField.getText(), "Страна сохранена некорректно");
        Assertions.assertEquals(userCity, cityField.getText(), "Город сохранён некорректно");
        Assertions.assertEquals(userEnglishLevel, englishLevelField.getText(), "Уровень знания английского сохранён некорректно");
        inputForm.assertRadioButtonChoice(userReadyToRelocate, readyToRelocateTrue, readyToRelocateFalse);
        inputForm.assertCheckboxChoice(fullTimeString, workFormatFullTime);
        inputForm.assertCheckboxChoice(partTimeString, workFormatFlexible);
        inputForm.assertCheckboxChoice(remoteString, workFormatRemote);
        logger.info("Основная информация сохранена корректно");
        return this;
    }

    public AboutMePage assertContactInfo() {
        Assertions.assertFalse(driver.findElement(By.cssSelector("#id_email")).getAttribute("value").isEmpty(), "Почта не указана");
        Assertions.assertFalse(driver.findElement(By.cssSelector("#id_phone")).getAttribute("value").isEmpty(), "Телефон не указан");
        List<WebElement> networkBlocks = driver.findElements(networkBlockLocator);
        Assertions.assertTrue(networkBlocks.size() == 2, "Количество добавленных соцсетей некорректно");
        Assertions.assertEquals(userNetworkFirst.toLowerCase(), driver.findElement(By.cssSelector("input[name='contact-0-service']")).getAttribute("value").toLowerCase(), "название первой соцсети сохранено некорректно");
        Assertions.assertEquals(userNetworkUserNameFirst, networkFirstValueField.getAttribute("value"), "имя пользователя в первой соцсети сохранено некорректно");
        Assertions.assertEquals(userNetworkSecond.toLowerCase(), driver.findElement(By.cssSelector("input[name='contact-1-service']")).getAttribute("value").toLowerCase(), "название второй соцсети сохранено некорректно");
        Assertions.assertEquals(userNetworkUserNameSecond, networkSecondValueField.getAttribute("value"), "имя пользователя во второй соцсети сохранено некорректно");
        logger.info("Контактная информация сохранена корректно");
        return this;
    }

    public AboutMePage assertOtherInfo() {
        select = new Select(genderField);
        Assertions.assertEquals(userGender, select.getFirstSelectedOption().getText(), "Пол пользователя сохранён некорректно");
        inputForm.assertInputFields(InputFieldData.COMPANY, userCompany);
        inputForm.assertInputFields(InputFieldData.POSITION, userPosition);
        logger.info("Раздел 'Другое' заполнен корректно");
        return this;
    }

    public AboutMePage assertDevInfo() {
        List<WebElement> devBlocks = driver.findElements(exeperienceBlockLocator);
        Assertions.assertTrue(devBlocks.size() == 1, "Количество записей в блоке 'Опыт разработки' некорректно");
        select = new Select(devLanguageSelector);
        Assertions.assertEquals(userDevLanguageFromEnum.getName(), select.getFirstSelectedOption().getText(), "Язык разработки сохранён некорректно");
        select = new Select(devExperienceSelector);
        Assertions.assertEquals(userDevExperienceFromEnum.getName(), select.getFirstSelectedOption().getText(), "Опыт разработки сохранён некорректно");
        logger.info("Опыт разработки заполнен корректно");
        return this;
    }
}
