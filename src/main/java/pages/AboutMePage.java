package pages;

import components.InputFormComponent;
import data.*;
import data.textFieldsLocators.InputFieldData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


public class AboutMePage extends AbsBasePage {

    public AboutMePage(WebDriver driver) {
        super(driver);
    }

    private String aboutPagepath = "/lk/biography/personal/";


    public void openAboutMePage() {
        open(aboutPagepath, driver);
    }

    @FindBy(css = "input[type='file']")
    private WebElement addAvatar;

    @FindBy(css = "[class='modal settings-photo-modal'] button")
    private WebElement modalConfirmation;

    @FindBy(css = "input[title='День рождения']")
    private WebElement birthDateField;

    @FindBy(css = "input[name='country']+ div")
    private WebElement countryField;

    @FindBy(css = "input[data-title='Город']+div")
    private WebElement cityField;

    @FindBy(css = "input[data-title='Уровень знания английского языка']+div")
    private WebElement englishLevelField;

    @FindBy(css = "input[type='radio'][value='True']")
    private WebElement readyToRelocateTrue;

    @FindBy(css = "input[type='radio'][value='False']")
    private WebElement readyToRelocateFalse;

    @FindBy(css = "input[title='Полный день']")
    private WebElement fullTimeCheckBox;

    @FindBy(css = "input[title='Гибкий график']")
    private WebElement partTimeCheckBox;

    @FindBy(css = "input[title='Удаленно']")
    private WebElement remoteCheckBox;

    @FindBy(css = "button[class$='lk-cv-custom-select-add']")
    private WebElement addNetworkButton;

    @FindBy(css = "select[name='gender']")
    private WebElement genderField;

    @FindBy(css = "a[class^='experience-add']")
    private WebElement addExperienceButton;

    @FindBy(css = "select[name='experience-0-experience']")
    private WebElement devLanguageSelector;

    @FindBy(css = "select[name='experience-0-level']")
    private WebElement devExperienceSelector;

    @FindBy(xpath = "//input[@name='contact-0-service']/..")
    private WebElement networkFirstTypeSelectWebelement;

    @FindBy(css = "input[name='contact-0-value']")
    private WebElement networkFirstValueField;

    @FindBy(xpath = "//input[@name='contact-1-service']/..")
    private WebElement networkSecondTypeSelectorWebelement;

    @FindBy(css = "input[name='contact-1-value']")
    private WebElement networkSecondValueField;

    @FindBy(css = "button[title='Сохранить и продолжить']")
    private WebElement saveAndContinueButton;

    private By networkBlockLocator = By.xpath("//div[@data-prefix='contact']//div[contains(@class, 'formset-row')]");
    private By exeperienceBlockLocator = By.xpath("//div[@data-prefix='experience']//div[contains(@class, 'formset-row')]");
    private InputFormComponent inputForm = new InputFormComponent(driver);
    private Select select = null;

    public AboutMePage fillPersonalData(String nameRu, String lastnameRu, String nameEn, String lastNameEn, String blogName, Date birthDate, String imagePath) {

        inputForm.loadImage(addAvatar, imagePath, modalConfirmation);
        inputForm.fillInputFields(InputFieldData.FIRSTNAMERU, nameRu);
        inputForm.fillInputFields(InputFieldData.LASTNAMERU, lastnameRu);
        inputForm.fillInputFields(InputFieldData.FIRSTNAMEEN, nameEn);
        inputForm.fillInputFields(InputFieldData.LASTNAMEEN, lastNameEn);
        inputForm.fillInputFields(InputFieldData.BLOGNAME, blogName);
        inputForm.fillTheDateField(birthDateField, birthDate);
        logger.info("заполнен блок 'Персональные данные'");
        return this;
    }

    public AboutMePage fillGeneralInfo(String country, String city, String englishLevel, Boolean readyToRelocate) {
        String countrySelector = "button[title='" + country + "']";
        String citySelector = "button[title='" + city + "']";
        String englishLevelSelector = "button[title='" + englishLevel + "']";
        By countryListSelector = By.xpath("//input[@name='country']/../following-sibling::div");
        inputForm.popupInteraction(countryField, countryListSelector, countrySelector);
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(driver.findElement(By.cssSelector("input[data-title='Город']")), "value", "disabled")));
        By cityListSelector = By.xpath("//input[@name='city']/../following-sibling::div");
        inputForm.popupInteraction(cityField, cityListSelector, citySelector);
        By englishLevelListSelector = By.xpath("//input[@name='english_level']/../following-sibling::div");
        inputForm.popupInteraction(englishLevelField, englishLevelListSelector, englishLevelSelector);
        inputForm.radioButtonInteraction(readyToRelocateTrue, readyToRelocateFalse, readyToRelocate);
        logger.info("заполнен блок 'Основная информация'");
        return this;

    }

    public AboutMePage chooseWorkFormat(Boolean fulltime, Boolean flexible, Boolean remote) {
        inputForm.checkboxInteraction(fullTimeCheckBox, fulltime, WorkFormatData.FULLTIME.getName());
        inputForm.checkboxInteraction(partTimeCheckBox, flexible, WorkFormatData.FLEXIBLE.getName());
        inputForm.checkboxInteraction(remoteCheckBox, remote, WorkFormatData.REMOTE.getName());
        logger.info("указан формат работы");
        return this;

    }

    public AboutMePage fillContactInfo(String firstUsername, String firstNetwork, String secondUsername, String secondNetwork) {
        By deleteLocator = By.xpath("//div[@data-prefix='contact']//div[contains(@class, 'formset-row')]//div[3]//button[contains(@class, 'delete')]");
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
        By list = By.xpath("//div[@data-prefix='contact']//div[contains(@class, 'formset-row')]//div[contains(@class, 'options-container')][not(contains(@class,'hide'))]");
        networkFirstTypeSelectWebelement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(list));
        String userFirstNetworkTypeSelector = String.format("//div[@data-num='0']//button[@data-value='%s']", firstNetwork);
        driver.findElement(By.xpath(userFirstNetworkTypeSelector)).click();
        networkFirstValueField.clear();
        networkFirstValueField.sendKeys(firstUsername);
        String userSecondNetworkTypeSelector = String.format("//div[@data-num='1']//button[@data-value='%s']", secondNetwork);
        networkSecondTypeSelectorWebelement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(list));
        driver.findElement(By.xpath(userSecondNetworkTypeSelector)).click();
        networkSecondValueField.clear();
        networkSecondValueField.sendKeys(secondUsername);
        logger.info("Заполнен блок 'Контактная информация'");
        return this;
    }

    public AboutMePage fillOtherInfo(String gender, String company, String position) {
        select = new Select(genderField);
        if (gender.equalsIgnoreCase(GenderData.MALE.getName())) {
            select.selectByValue(GenderData.MALE.getOption());
        } else {
            select.selectByValue(GenderData.FEMALE.getOption());
        }
        inputForm.fillInputFields(InputFieldData.COMPANY, company);
        inputForm.fillInputFields(InputFieldData.POSITION, position);
        logger.info("заполнен блок 'Другое'");
        return this;
    }

    public AboutMePage fillDevInfo(DevLanguagesData devLanguage, DevExperienceData devExperience) {
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
        select.selectByValue(devLanguage.getOption());
        select = new Select(devExperienceSelector);
        select.selectByValue(devExperience.getOption());
        logger.info("заполнен блок 'Опыт разработки'");
        return this;
    }

    public AboutMePage saveAll() {
        saveAndContinueButton.click();
        wait.until(ExpectedConditions.urlContains("skills"));
        logger.info("данные на странице сохранены");
        return this;
    }

    public AboutMePage assertPersonalInfo(String nameRu, String lastnameRu, String nameEn, String lastNameEn, String blogName, Date birthDate) {
        inputForm.assertInputFields(InputFieldData.FIRSTNAMERU, nameRu);
        inputForm.assertInputFields(InputFieldData.LASTNAMERU, lastnameRu);
        inputForm.assertInputFields(InputFieldData.FIRSTNAMEEN, nameEn);
        inputForm.assertInputFields(InputFieldData.LASTNAMEEN, lastNameEn);
        inputForm.assertInputFields(InputFieldData.BLOGNAME, blogName);
        LocalDate thisDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(thisDate);
        Assertions.assertEquals(date, birthDateField.getAttribute("value"), "Дата рождения сохранена некорректно");
        Assertions.assertFalse(driver.findElement(By.cssSelector("[class^='settings-photo__photo']")).getAttribute("style").isEmpty(), "Изображение не загружено");
        logger.info("Персональные данные сохранены корректно");
        return this;
    }

    public AboutMePage assertGeneralInfo(String country, String city, String englishLevel, Boolean readyToRelocate) {
        Assertions.assertEquals(country, countryField.getText(), "Страна сохранена некорректно");
        Assertions.assertEquals(city, cityField.getText(), "Город сохранён некорректно");
        Assertions.assertEquals(englishLevel, englishLevelField.getText(), "Уровень знания английского сохранён некорректно");
        inputForm.assertRadioButtonChoice(readyToRelocateTrue, readyToRelocateFalse, readyToRelocate);
        logger.info("Основная информация сохранена корректно");
        return this;
    }

    public AboutMePage assertWorkFormat(Boolean fulltime, Boolean flexible, Boolean remote) {
        inputForm.assertCheckboxChoice(fullTimeCheckBox, fulltime);
        inputForm.assertCheckboxChoice(partTimeCheckBox, flexible);
        inputForm.assertCheckboxChoice(remoteCheckBox, remote);
        logger.info("Формат работы сохранён корректно");
        return this;
    }

    public AboutMePage assertContactInfo(String firstUsername, String firstNetwork, String secondUsername, String secondNetwork) {
        Assertions.assertFalse(driver.findElement(By.cssSelector("#id_email")).getAttribute("value").isEmpty(), "Почта не указана");
        Assertions.assertFalse(driver.findElement(By.cssSelector("#id_phone")).getAttribute("value").isEmpty(), "Телефон не указан");
        List<WebElement> networkBlocks = driver.findElements(networkBlockLocator);
        Assertions.assertTrue(networkBlocks.size() == 2, "Количество добавленных соцсетей некорректно");
        Assertions.assertEquals(firstNetwork.toLowerCase(), driver.findElement(By.cssSelector("input[name='contact-0-service']")).getAttribute("value").toLowerCase(), "название первой соцсети сохранено некорректно");
        Assertions.assertEquals(firstUsername, networkFirstValueField.getAttribute("value"), "имя пользователя в первой соцсети сохранено некорректно");
        Assertions.assertEquals(secondNetwork.toLowerCase(), driver.findElement(By.cssSelector("input[name='contact-1-service']")).getAttribute("value").toLowerCase(), "название второй соцсети сохранено некорректно");
        Assertions.assertEquals(secondUsername, networkSecondValueField.getAttribute("value"), "имя пользователя во второй соцсети сохранено некорректно");
        logger.info("Контактная информация сохранена корректно");
        return this;
    }

    public AboutMePage assertOtherInfo(String gender, String company, String position) {
        select = new Select(genderField);
        Assertions.assertEquals(gender, select.getFirstSelectedOption().getText(), "Пол пользователя сохранён некорректно");
        inputForm.assertInputFields(InputFieldData.COMPANY, company);
        inputForm.assertInputFields(InputFieldData.POSITION, position);
        logger.info("Раздел 'Другое' заполнен корректно");
        return this;
    }

    public AboutMePage assertDevInfo(DevLanguagesData devLanguage, DevExperienceData devExperience) {
        List<WebElement> devBlocks = driver.findElements(exeperienceBlockLocator);
        Assertions.assertTrue(devBlocks.size() == 1, "Количество записей в блоке 'Опыт разработки' некорректно");
        select = new Select(devLanguageSelector);
        Assertions.assertEquals(devLanguage.getName(), select.getFirstSelectedOption().getText(), "Язык разработки сохранён некорректно");
        select = new Select(devExperienceSelector);
        Assertions.assertEquals(devExperience.getName(), select.getFirstSelectedOption().getText(), "Опыт разработки сохранён некорректно");
        logger.info("Опыт разработки заполнен корректно");
        return this;
    }
}
