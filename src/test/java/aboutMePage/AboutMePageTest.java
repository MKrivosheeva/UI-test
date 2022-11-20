package aboutMePage;

import components.HeaderMenuComponent;
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

public class AboutMePageTest {

    private WebDriver driver;
    public org.apache.logging.log4j.Logger logger = LogManager.getLogger(AboutMePageTest.class);
    private String testLogin = System.getProperty("testStudent.login");
    private String testPassword = System.getProperty("testStudent.password");
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
        SignInComponent signInComponent = new SignInComponent(driver);
        HeaderMenuComponent headerMenu = new HeaderMenuComponent(driver);
        headerMenu.initSignIn();
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
    @Order(1) //так не делать, объединить
    public void fillAboutMePage() { //написать сплошным тестом
        AboutMePage aboutMePage = new AboutMePage(driver);
        aboutMePage.open(aboutPagepath);
//        aboutMePage.fillPersonalData();
//        aboutMePage.fillGeneralInfo();
       aboutMePage.fillContactInfo();
//        aboutMePage.fillOtherInfo();
//        aboutMePage.fillDevInfo();
 //       aboutMePage.saveAll();
//        //закрыть сессию браузер
//        aboutMePage.close();
//        driver.get(System.getProperty("base.url"));
//        driver.switchTo().window((driver.getTitle()));
//
//
//        //потом драйвер гет
//        //переключиться на новую сессию swithcTOwindow передать айди окна из getheader
//        //
//    }
//    @Test
//    @Order(2)
//    public void valiateAboutMePage() {
//           AboutMePage aboutMePage = new AboutMePage(driver);
//           aboutMePage.open(aboutPagepath);
        //   aboutMePage.assertPersonalInfo();
       //  aboutMePage.assertGeneralInfo();
    //   aboutMePage.assertContactInfo();
//           aboutMePage.assertOtherInfo();
        //    aboutMePage.assertDevInfo();
    }
}

