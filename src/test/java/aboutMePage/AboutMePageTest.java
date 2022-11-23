package aboutMePage;

import data.*;
import data.countriesAndCities.CitiesData;
import data.countriesAndCities.CountriesData;
import driver.DriverFactory;
import exception.DriverNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.AboutMePage;
import usefulCommonInteractions.AbsPageObject;
import usefulCommonInteractions.AuthorizationStepsForTests;
import java.util.Date;


public class AboutMePageTest extends AbsPageObject {

    private WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(AboutMePageTest.class);
    private String userRuFirstName = ruFaker.name().firstName();
    private String userRuLastName = ruFaker.name().lastName();
    private String userEnFirstName = enFaker.name().firstName();
    private String userEnLastName = enFaker.name().lastName();
    private String userEnBlogName = enFaker.name().firstName();
    private Date userBirthDate = dateFaker.date().birthday(18, 105);
    private String imagePath = "/src/main/java/data/images/avatar.jpg";

    private String userCountry = CountriesData.MOLDOVA.getName();
    private String userCity = CitiesData.BELCI.getName();
    private String userEnglishLevel = EnglishLevelData.ADVANCED.getName();
    private Boolean userReadyToRelocate = ReadyToRelocateData.FALSE.getValue();
    private Boolean workFormatFlexible = false;
    private Boolean workFormatRemote = true;
    private Boolean workFormatFullTime = false;

    private String userNetworkUserNameFirst = enFaker.name().username();
    private String userNetworkUserNameSecond = enFaker.name().username();
    private String userNetworkFirst = SocialNetworksData.FACEBOOK.getName();
    private String userNetworkSecond = SocialNetworksData.TELEGRAM.getName();

    private String userGender = GenderData.FEMALE.getName();
    private String userCompany = ruFaker.company().name();
    private String userPosition = ruFaker.company().profession();

    private DevLanguagesData userDevLanguage = DevLanguagesData.RUBY;
    private DevExperienceData userDevExperience = DevExperienceData.TWOYEARS;

    @BeforeAll
    public static void load() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() throws DriverNotSupportedException {
        this.driver = new DriverFactory().getDriver();
        AuthorizationStepsForTests authorization = new AuthorizationStepsForTests(driver);
        authorization.authorization(driver);
    }

    @AfterEach
    public void close() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    public void fillAboutMePage() throws DriverNotSupportedException {
        AboutMePage aboutMePage = new AboutMePage(driver);
        aboutMePage.openAboutMePage();
        aboutMePage.fillPersonalData(userRuFirstName, userRuLastName, userEnFirstName, userEnLastName, userEnBlogName, userBirthDate, imagePath);
        aboutMePage.fillGeneralInfo(userCountry, userCity, userEnglishLevel, userReadyToRelocate);
        aboutMePage.chooseWorkFormat(workFormatFullTime, workFormatFlexible, workFormatRemote);
        aboutMePage.fillContactInfo(userNetworkUserNameFirst, userNetworkFirst, userNetworkUserNameSecond, userNetworkSecond);
        aboutMePage.fillOtherInfo(userGender, userCompany, userPosition);
        aboutMePage.fillDevInfo(userDevLanguage, userDevExperience);
        aboutMePage.saveAll();
        driver.quit();
        driver = new DriverFactory().getDriver();
        AuthorizationStepsForTests authorization = new AuthorizationStepsForTests(driver);
        authorization.authorization(driver);
        aboutMePage = new AboutMePage(driver);
        aboutMePage.openAboutMePage();
        aboutMePage.assertPersonalInfo(userRuFirstName, userRuLastName, userEnFirstName, userEnLastName, userEnBlogName, userBirthDate);
        aboutMePage.assertGeneralInfo(userCountry, userCity, userEnglishLevel, userReadyToRelocate);
        aboutMePage.assertWorkFormat(workFormatFullTime, workFormatFlexible, workFormatRemote);
        aboutMePage.assertContactInfo(userNetworkUserNameFirst, userNetworkFirst, userNetworkUserNameSecond, userNetworkSecond);
        aboutMePage.assertOtherInfo(userGender, userCompany, userPosition);
        aboutMePage.assertDevInfo(userDevLanguage, userDevExperience);
        logger.info("Тест пройден");
    }
}

