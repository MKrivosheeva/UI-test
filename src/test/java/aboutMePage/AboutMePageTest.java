package aboutMePage;

import components.SignInComponent;
import driver.DriverFactory;
import exception.DriverNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.AboutMePage;
import pages.MainPage;
import java.util.concurrent.TimeUnit;

public class AboutMePageTest {

    private WebDriver driver;
    public org.apache.logging.log4j.Logger logger = LogManager.getLogger(AboutMePageTest.class);
    private String testLogin = System.getProperty("testStudent.login");
    private String testPassword = System.getProperty("testStudent.password");

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
        SignInComponent signInComponent = new SignInComponent(driver);
        signInComponent.signIn(testLogin, testPassword);
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
        aboutMePage.open();
     //   aboutMePage.fillPersonalData();
        aboutMePage.fillGeneralInfo();
//        aboutMePage.fillContactInfo();
//        aboutMePage.fillOtherInfo();
//        aboutMePage.fillDevInfo();
        aboutMePage.saveAll();

//        ProfileDropDownComponent userMenu = new ProfileDropDownComponent(driver);
//        SignInComponent signInComponent = new SignInComponent(driver);
//        userMenu.unSign();
    }
//    @Test
//    public void valiateAboutMePage() {
//           AboutMePage aboutMePage = new AboutMePage(driver);
//           aboutMePage.open();
//           driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); //исправить ожидание
//           aboutMePage.assertPersonalInfo();
//           aboutMePage.assertGeneralInfo();
//           aboutMePage.assertConactInfo();
//           aboutMePage.assertOtherInfo();
//           aboutMePage.assertDevInfo();
//    }
}

