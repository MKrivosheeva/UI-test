package usefulCommonInteractions;

import components.HeaderMenuComponent;
import components.SignInComponent;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;

public class AuthorizationStepsForTests extends AbsPageObject{

    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(AuthorizationStepsForTests .class);
    private String testLogin = System.getProperty("testStudent.login");
    private String testPassword = System.getProperty("testStudent.password");

    @FindBy(css="button[class~='cookies__button']")
    private WebElement cookiesButton;

    public AuthorizationStepsForTests(WebDriver driver) {
        super(driver);
    }

    public void authorization(WebDriver driver){
        SignInComponent signInComponent = new SignInComponent(driver);
        HeaderMenuComponent headerMenu = new HeaderMenuComponent(driver);
        new MainPage(driver)
                .open("/", driver);
        logger.info("Открыта главная страница");
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton)).click();
        logger.info("Куки одобрены");
        headerMenu.initSignIn();
        signInComponent.signIn(testLogin, testPassword);
        logger.info("Пользователь успешно авторизовался на сайте.");
    }
}
