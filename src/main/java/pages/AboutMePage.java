//картинка - как сделать проверку - что там есть файл, не пусто.
//тест-дизайн: надо разлогиниться и запустить ассёрты или сделать новый тест - один за другим?
//как отключить все всплывашки и принять куки в настройках браузера
//куками

//доделать: чек-боксы,
//построить локаторы для второго контакта
//к дата-нам верхнего уровня

package pages;

import components.InputFormComponent;
import data.*;
import data.countriesAndCities.CitiesData;
import data.countriesAndCities.CountriesData;
import data.textFieldsLocators.InputFieldData;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AboutMePage extends AbsBasePage {


   public AboutMePage(WebDriver driver) {
      super(driver);
   }

//   private String aboutPagepath = "/lk/biography/personal/";
//   public void open() {
//          driver.get(BASE_URL + aboutPagepath);
//   }

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
   private Boolean workFormatFlexible = false; //убрать в енам и передавать рандомно
   private Boolean workFormatRemote = true;
   private Boolean workFormatFullTime = false;

   private String userGender = GenderData.FEMALE.getName();

   private DevLanguagesData userDevLanguageFromEnum = DevLanguagesData.RUBY;
   private DevExperienceData userDevExperienceFromEnum = DevExperienceData.TWOYEARS;

   private String userNetworkFirst = SocialNetworksData.FACEBOOK.getName();
   private String userNetworkSecond = SocialNetworksData.TELEGRAM.getName();

   private String fullTimeString = "input[title='Полный день']";
   private String partTimeString = "input[title='Гибкий график']";
   private String remoteString = "input[title='Удаленно']";

   private String countrySelector = "button[title='"+userCountry + "']";
   private String citySelector = "button[title='"+ userCity + "']";
   private String englishLevelSelector = "button[title='"+ userEnglishLevel + "']";
   private String readyToRelocateTrue = "input[type='radio'][value='True']";
   private String readyToRelocateFalse = "input[type='radio'][value='False']";
   private String userFirstNetworkTypeSelector = "button[title='"+userNetworkFirst+"']";
   private String userSecondNetworkTypeSelector = "button[data-value='"+userNetworkSecond+"']";

   private By birthDateLocator = By.cssSelector("input[title='День рождения']");
   private By country = By.cssSelector("input[name='country']+ div"); //в класс с селектом над лэйблом
   private By city = By.cssSelector("input[data-title='Город']+div");//то же самое


   private By englishLevel = By.cssSelector("input[data-title='Уровень знания английского языка']+div");
//   private By readyToRelocateTrue = By.cssSelector("input[type='radio'][value='True']");
//   private By readyToRelocateFalse = By.cssSelector("input[type='radio'][value='False']");
   private By genderSelector = By.cssSelector("select[name='gender']");
   private By addExperienceSelector = By.cssSelector("a[class^='experience-add']");
   private By devLanguageSelector = By.cssSelector("select[name='experience-0-experience']");
   private By devExperienceSelector = By.cssSelector("select[name='experience-0-level']");


   private By networkFirstTypeSelector = By.cssSelector("input[name='contact-0-service']");
   private By networkFirstValueSelector = By.cssSelector("input[name='contact-0-value']");
   private By networkSecondTypeSelector = By.cssSelector("input[name='contact-1-service']");
   private By networkSecondValueSelector = By.cssSelector("input[name='contact-1-value']");
   private By addNetworkButton = By.cssSelector("button[class$='custom-select-add']");
   private By saveAndContinueButton = By.cssSelector("button[title='Сохранить и продолжить']");
   private InputFormComponent inputForm = new InputFormComponent(driver);

   private String addAvatarSelector = "input[type='file']";
   private String imagePath = "/src/main/java/data/images/avatar.jpg";
   private String modalConfirmation = "[class='modal settings-photo-modal'] button";
   private Select select = null;

   public AboutMePage fillPersonalData() {

      inputForm.loadImage(addAvatarSelector, imagePath, modalConfirmation);
      inputForm.fillInputFields(InputFieldData.FIRSTNAMERU, userRuFirstName);
      inputForm.fillInputFields(InputFieldData.LASTNAMERU, userRuLastName);
      inputForm.fillInputFields(InputFieldData.FIRSTNAMEEN, userEnFirstName);
      inputForm.fillInputFields(InputFieldData.LASTNAMEEN, userEnLastName);
      inputForm.fillInputFields(InputFieldData.BLOGNAME, userEnBlogName);
      inputForm.fillTheDateField(birthDateLocator, userBirthDate);
      logger.info("заполнен блок 'Персональные данные'");
   return this;
   }

   public AboutMePage fillGeneralInfo ()  {
   By countryListSelector = By.xpath("//input[@name='country']/../following-sibling::div");
   inputForm.popupInteraction(country,countryListSelector, countrySelector);
   wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(city, "value disabled", "disabled")));
   By cityListSelector = By.xpath("//input[@name='city']/../following-sibling::div");
   inputForm.popupInteraction(city, cityListSelector, citySelector);
    By englishLevelListSelector = By.xpath("//input[@name='english_level']/../following-sibling::div");
    inputForm.popupInteraction(englishLevel, englishLevelListSelector, englishLevelSelector);
   // By englishLevelList = By.xpath("//input[@data-title='Уровень знания английского языка']/../following-sibling::div");
  //  wait.until((ExpectedConditions.attributeContains(englishLevelList, "class", "hide")));
//
    inputForm.radioButtonInteraction(readyToRelocateFalse, readyToRelocateTrue, userReadyToRelocate);
    inputForm.checkboxInteraction(fullTimeString, workFormatFullTime);
    inputForm.checkboxInteraction(partTimeString, workFormatFlexible);
    inputForm.checkboxInteraction(remoteString, workFormatRemote);
    logger.info("заполнен блок 'Основная информация'");
    return this;

     }
//   public AboutMePage fillContactInfo () { //добавить нажатие кнопки добавить или удалить перед заполнением данных
//    inputForm.popupInteraction(networkFirstTypeSelector, userFirstNetworkTypeSelector);
//   // inputForm.fillTextField(networkFirstValueSelector, userNetworkUserNameFirst);
//
////    inputForm.clickSimpleButton(addNetworkButton);  //неправильно построен локатор для второго контакта
////    inputForm.popupInteraction(networkSecondTypeSelector, userSecondNetworkTypeSelector);//вторая не добавляется
////    inputForm.fillTextField(networkSecondValueSelector, userNetworkUserNameSecond);
//    logger.info("заполнен блок 'Контактная информация'");
//    return this;
    //class="lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container hide"
    //вот этот элемент скрывается class="container__col container__col_12 container__col_middle"
  //вот это блок верхнеуровневый
    //если меньше двух - добавить ещё один, если больше - удалить
// }

   public AboutMePage fillOtherInfo () {
   select = new Select(driver.findElement(genderSelector));
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
   public AboutMePage fillDevInfo () {
   By exeperienceBlockLocator = By.cssSelector("[class='experience-row js-formset-row']");
   By deleteLocator = By.cssSelector("[class^='experience-row__remove']");
   List<WebElement> delButtons = driver.findElements(deleteLocator);
    if (driver.findElements(exeperienceBlockLocator).size() > 1) {
        for (int i = (driver.findElements(exeperienceBlockLocator).size()); i>1; i--) {
           delButtons.get(i-1).click();
       }
    }
   if (driver.findElements(exeperienceBlockLocator).size() == 0) {
       driver.findElement(addExperienceSelector).click();
       wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='experience-row js-formset-row']")));
   }
       select = new Select(driver.findElement(devLanguageSelector));
       select.selectByValue(userDevLanguageFromEnum.getOption());
       select = new Select(driver.findElement(devExperienceSelector));
       select.selectByValue(userDevExperienceFromEnum.getOption());
      logger.info("заполнен блок 'Опыт разработки'");
   return this;
   }
   public AboutMePage saveAll () {
      driver.findElement(saveAndContinueButton).click();
      wait.until(ExpectedConditions.urlContains("skills"));
      logger.info("данные сохранены");
      return this;
   }

   public AboutMePage assertPersonalInfo () {
       inputForm.assertInputFields(InputFieldData.FIRSTNAMERU, userRuFirstName);
       inputForm.assertInputFields(InputFieldData.LASTNAMERU, userRuLastName);
       inputForm.assertInputFields(InputFieldData.FIRSTNAMEEN, userEnFirstName);
       inputForm.assertInputFields(InputFieldData.LASTNAMEEN, userEnLastName);
       inputForm.assertInputFields(InputFieldData.BLOGNAME, userEnBlogName);
   SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");
   String date = formatter.format(userBirthDate);
   Assertions.assertEquals(date, driver.findElement(birthDateLocator).getAttribute("value"), "Дата рождения сохранена некорректно");
   Assertions.assertFalse(driver.findElement(By.cssSelector("[class^='settings-photo__photo']")).getAttribute("style").isEmpty(), "Изображение не загружено");
   logger.info("Персональные данные сохранены корректно");
   return this;
   }

  public AboutMePage assertGeneralInfo () {


  inputForm.assertRadioButtonChoice(userReadyToRelocate, readyToRelocateTrue, readyToRelocateFalse);
  inputForm.assertCheckboxChoice(fullTimeString, workFormatFullTime);
  inputForm.assertCheckboxChoice(partTimeString, workFormatFlexible);
  inputForm.assertCheckboxChoice(remoteString, workFormatRemote);

  logger.info("Основная информация сохранена корректно");
   return this;
   }

  public AboutMePage assertContactInfo () { //добавить проверку, что будет два блока

  //сделать массив, получить два элемента и проверить, что их два и содержание поля
      //
      //
      //
      //
//проверить, что почта и телефон не пустые
   return this;
   }

    public AboutMePage assertOtherInfo () {
    select = new Select(driver.findElement(genderSelector));
    Assertions.assertEquals(userGender, select.getFirstSelectedOption().getText(), "Пол пользователя сохранён некорректно");
    inputForm.assertInputFields(InputFieldData.COMPANY, userCompany);
    inputForm.assertInputFields(InputFieldData.POSITION, userCompany);
//    Assertions.assertEquals(userCompany,driver.findElement(companySelector).getAttribute("value"));
//    Assertions.assertEquals(userPosition, driver.findElement(positionSelector).getAttribute("value"));
    logger.info("Раздел 'Другое' заполнен корректно");
    return this;
   }

    public AboutMePage assertDevInfo () {
        By exeperienceBlockLocator = By.cssSelector("[class='experience-row js-formset-row']");
        List<WebElement> devBlocks = driver.findElements(exeperienceBlockLocator);
        Assertions.assertTrue(devBlocks.size()==1, "Количество записей в блоке 'Опыт разработки' некорректно");
        select = new Select(driver.findElement(devLanguageSelector));
        Assertions.assertEquals(userDevLanguageFromEnum.getName(), select.getFirstSelectedOption().getText(), "Язык разработки сохранён некорректно");
        select = new Select (driver.findElement(devExperienceSelector));
        Assertions.assertEquals(userDevExperienceFromEnum.getName(), select.getFirstSelectedOption().getText(), "Опыт разработки сохранён некорректно");
        logger.info("Опыт разработки заполнен корректно");
   return this;
   }
}