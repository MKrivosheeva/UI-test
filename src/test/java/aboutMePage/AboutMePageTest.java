package aboutMePage;

import driver.DriverFactory;
import exception.DriverNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.AboutMePage;
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
        AuthorizationStepsForTests authorization = new AuthorizationStepsForTests(driver);
        authorization.openAndAuthorization();
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
        driver.navigate().back();
        aboutMePage.assertPersonalInfo();
        aboutMePage.assertGeneralInfo();
        aboutMePage.assertContactInfo();
        aboutMePage.assertOtherInfo();
        aboutMePage.assertDevInfo();
        logger.info("Тест пройден");
    }
}

