//нужны ожидания на выпадающие списки, радиобаттоны и чек-боксы- медленно работают, быстро нет
//атрибут не содержит (toBecontains + not) - в классе нет hide, потом клик
//для поля город ожидание что нет атрибута disabled = true


//как построить локаторы для второго контакта
//к дата-нам верхнего уровня

package pages;

import components.InputFormComponent;
import data.*;
import data.countriesAndCities.CitiesData;
import data.countriesAndCities.CountriesData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AboutMePage extends AbsBasePage {


   public AboutMePage(WebDriver driver) {
      super(driver);
   }

   private String aboutPagepath = "/lk/biography/personal/";
   public void open() {
          driver.get(BASE_URL + aboutPagepath);
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

   private String userCountry = CountriesData.Russia.getName();
   private String userCity = CitiesData.Anapa.getName();
   private String userEnglishLevel = EnglishLevelData.ADVANCED.getName();
   private Boolean userReadyToRelocate = ReadyToRelocateData.TRUE.getValue();
   private String workFormat = WorkFormatData.FLEXIBLE.getName();

   private String userGender = GenderData.Female.getName();

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

   private By firstNameRu = By.cssSelector("#id_fname");
   private By firstNameEn = By.cssSelector("#id_fname_latin");
   private By lastNameRu = By.cssSelector("#id_lname");
   private By lastNameEn = By.cssSelector("#id_lname_latin");
   private By blogName = By.cssSelector("#id_blog_name");
   private By birthDateLocator = By.cssSelector("input[title='День рождения']");
   private By country = By.cssSelector("input[name='country']+ div");
   private By city = By.cssSelector("input[data-title='Город']+div");
   private By englishLevel = By.cssSelector("input[data-title='Уровень знания английского языка']+div");
//   private By readyToRelocateTrue = By.cssSelector("input[type='radio'][value='True']");
//   private By readyToRelocateFalse = By.cssSelector("input[type='radio'][value='False']");
   private By genderSelector = By.cssSelector("select[name='gender']");
   private By companySelector = By.cssSelector("input[name='company']");
   private By positionSelector = By.cssSelector("input[name='work']");
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

   public void fillPersonalData() {

      inputForm.loadImage(addAvatarSelector, imagePath, modalConfirmation);
      inputForm.fillTextField(firstNameRu, userRuFirstName);
      inputForm.fillTextField(lastNameRu, userRuLastName);
      inputForm.fillTextField(firstNameEn, userEnFirstName);
      inputForm.fillTextField(lastNameEn, userEnLastName);
      inputForm.fillTextField(blogName, userEnBlogName);
      inputForm.fillTheDateField(birthDateLocator, userBirthDate);
      logger.info("заполнен блок 'Персональные данные'");

   }

   public void fillGeneralInfo ()  {
//    inputForm.popupInteraction(country, countrySelector);
//    inputForm.popupInteraction(city, citySelector);
//    inputForm.popupInteraction(englishLevel, englishLevelSelector);
     inputForm.radioButtonInteraction(readyToRelocateFalse, readyToRelocateTrue, userReadyToRelocate);
//    if (userReadyToRelocate.equals(ReadyToRelocateData.TRUE.getName())) { //разобраться, почему ElementNotInteractableException
//       inputForm.radioButtonSetTrue(readyToRelocateTrue);
//    }
//    else {
//       inputForm.radioButtonSetFalse(readyToRelocateFalse);
//    }
//   if (workFormat.equals(WorkFormatData.FULLTIME.getName())) {
//         inputForm.checkboxSelect(fullTimeString);
//         inputForm.checkboxUnSelect(partTimeString);
//         inputForm.checkboxUnSelect(remoteString);
//      }
//      if (workFormat.equals(WorkFormatData.FLEXIBLE.getName())) {
//         inputForm.checkboxUnSelect(fullTimeString);
//         inputForm.checkboxSelect(partTimeString);
//         inputForm.checkboxUnSelect(remoteString);
//      }
//      if (workFormat.equals(WorkFormatData.REMOTE.getName())) {
//         inputForm.checkboxUnSelect(fullTimeString);
//         inputForm.checkboxUnSelect(partTimeString);
//         inputForm.checkboxSelect(remoteString);
//      }
    logger.info("заполнен блок 'Основная информация'");
   }
   public void fillContactInfo () {
    inputForm.popupInteraction(networkFirstTypeSelector, userFirstNetworkTypeSelector);
    inputForm.fillTextField(networkFirstValueSelector, userNetworkUserNameFirst);

//    inputForm.clickSimpleButton(addNetworkButton);  //неправильно построен локатор для второго контакта
//    inputForm.popupInteraction(networkSecondTypeSelector, userSecondNetworkTypeSelector);//вторая не добавляется
//    inputForm.fillTextField(networkSecondValueSelector, userNetworkUserNameSecond);
    logger.info("заполнен блок 'Контактная информация'");
   }

   public void fillOtherInfo () {
   select = new Select(driver.findElement(genderSelector));
       if (userGender.equalsIgnoreCase(GenderData.Male.getName())) {
          select.selectByValue(GenderData.Male.getOption());
       } else {
           select.selectByValue(GenderData.Female.getOption());
       }
   inputForm.fillTextField(companySelector, userCompany);
   inputForm.fillTextField(positionSelector, userPosition);
   logger.info("заполнен блок 'Другое'");
   }
   public void fillDevInfo () {
      inputForm.clickSimpleButton(addExperienceSelector);
      select = new Select(driver.findElement(devLanguageSelector));
      select.selectByValue(userDevLanguageFromEnum.getOption());
      select = new Select(driver.findElement(devExperienceSelector));
      select.selectByValue(userDevExperienceFromEnum.getOption());
      logger.info("заполнен блок 'Опыт разработки'");
   }
   public void saveAll () {
      inputForm.clickSimpleButton(saveAndContinueButton);
      wait.until(ExpectedConditions.urlContains("skills"));
      logger.info("данные сохранены");
   }


   public void assertPersonalInfo () {
   //wait.until(ExpectedConditions.visibilityOf(driver.findElement(firstNameRu)));
   Assertions.assertEquals(userRuFirstName, driver.findElement(firstNameRu).getAttribute("value"));
   Assertions.assertEquals(userRuLastName, driver.findElement(lastNameRu).getAttribute("value"));
   Assertions.assertEquals(userEnFirstName, driver.findElement(firstNameEn).getAttribute("value"));
   Assertions.assertEquals(userEnLastName, driver.findElement(lastNameEn).getAttribute("value"));
   Assertions.assertEquals(userEnBlogName, driver.findElement(blogName).getAttribute("value"));
    SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");
    String date = formatter.format(userBirthDate);
    Assertions.assertEquals(date, driver.findElement(birthDateLocator).getAttribute("value"));
 //  String fullStyleName = driver.findElement(By.cssSelector("[class^='settings-photo__photo']")).getAttribute("style");
  // String avatarUrl = "background-image: url(https://cdn.otus.ru/media/public/77/a3/avatar-326769-77a30e.png)";/сконструировать, как обрезать
//   Assertions.assertEquals(); скачивать картинку и сравнивать с оригиналом
//   Assertions.assertEquals();
   logger.info("Персональные данные сохранены корректно");
    }

  public void assertGeneralInfo () {


  }

  public void assertConactInfo () {

  //сделать массив, получить два элемента и проверить, что их два и содержание поля
      //
      //
      //
      //

   }

    public void assertOtherInfo () {
    select = new Select(driver.findElement(genderSelector));
    Assertions.assertEquals(userGender, select.getFirstSelectedOption().getText());
    Assertions.assertEquals(userCompany,driver.findElement(companySelector).getAttribute("value"));
    Assertions.assertEquals(userPosition, driver.findElement(positionSelector).getAttribute("value"));
     logger.info("Раздел 'Другое' заполнен корректно");
   }

    public void assertDevInfo () {
        select = new Select(driver.findElement(devLanguageSelector));
        Assertions.assertEquals(userDevLanguageFromEnum.getName(), select.getFirstSelectedOption().getText());
        select = new Select (driver.findElement(devExperienceSelector));
        Assertions.assertEquals(userDevExperienceFromEnum.getName(), select.getFirstSelectedOption().getText());
        logger.info("Опыт разработки заполнен корректно");
   }
}