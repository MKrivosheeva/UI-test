package usefulCommonInteractions;

import components.HeaderMenuComponent;
import components.SignInComponent;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class AuthorizationStepsForTests extends AbsPageObject{

    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(AuthorizationStepsForTests .class);
    private String testLogin = System.getProperty("testStudent.login");
    private String testPassword = System.getProperty("testStudent.password");

    public AuthorizationStepsForTests(WebDriver driver) {
        super(driver);
    }

    public void openAndAuthorization(){
        new MainPage(driver)
                .open("/");
        logger.info("Открыта главная страница");
        driver.findElement(By.cssSelector("button[class='js-cookie-accept cookies__button']")).click();
        logger.info("Куки одобрены");
        SignInComponent signInComponent = new SignInComponent(driver);
        HeaderMenuComponent headerMenu = new HeaderMenuComponent(driver);
        headerMenu.initSignIn();
        signInComponent.signIn(testLogin, testPassword);
        logger.info("Пользователь успешно авторизовался на сайте.");

    }
}
