package aboutMePage;

import components.HeaderMenuComponent;
import driver.DriverFactory;
import exception.DriverNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.AboutMePage;
import pages.MainPage;
import usefulCommonInteractions.AuthorizationStepsForTests;

public class AboutMePageTest {

    private WebDriver driver;
    public org.apache.logging.log4j.Logger logger = LogManager.getLogger(AboutMePageTest.class);
    private String aboutPagepath = "/lk/biography/personal/";

    @BeforeAll
    public static void load() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() throws DriverNotSupportedException {
        this.driver = new DriverFactory().getDriver();
        new MainPage(driver)
                .open("/");
        logger.info("Открыта главная страница");
        driver.findElement(By.cssSelector("button[class='js-cookie-accept cookies__button']")).click();
        logger.info("Куки одобрены");
        AuthorizationStepsForTests authorization = new AuthorizationStepsForTests(driver);
        authorization.authorization();
    }

    @AfterEach
    public void close() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    public void fillAboutMePage() {
        AboutMePage aboutMePage = new AboutMePage(driver);
        aboutMePage.open(aboutPagepath);
        aboutMePage.fillPersonalData();
        aboutMePage.fillGeneralInfo();
        aboutMePage.fillContactInfo();
        aboutMePage.fillOtherInfo();
        aboutMePage.fillDevInfo();
        aboutMePage.saveAll();
        HeaderMenuComponent headermenu = new HeaderMenuComponent(driver);
        headermenu.unSign();
        AuthorizationStepsForTests authorization = new AuthorizationStepsForTests(driver);
        authorization.authorization();
        aboutMePage.open(aboutPagepath);
        aboutMePage.assertPersonalInfo();
        aboutMePage.assertGeneralInfo();
        aboutMePage.assertContactInfo();
        aboutMePage.assertOtherInfo();
        aboutMePage.assertDevInfo();
        logger.info("Тест пройден");
    }
}

