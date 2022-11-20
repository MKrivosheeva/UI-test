package components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderMenuComponent extends AbsBaseComponent{

    public HeaderMenuComponent(WebDriver driver) {

        super(driver);
    }

    @FindBy(css = "button[data-modal-id=new-log-reg]")
    WebElement signInButton;

    public SignInComponent initSignIn() {
        signInButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[class='modal-container new-log-reg-container']"))));
        SignInComponent authorizationWindow = new SignInComponent(driver);

    return authorizationWindow;
    }
    public HeaderMenuComponent unSign() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String exitSelector = "a[title='Выход']";
        String profileMenuSelector = "[class$=username]";
        WebElement profileMenu = driver.findElement(By.cssSelector(profileMenuSelector));
        js.executeScript("arguments[0].click();", profileMenu);
        js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(exitSelector)));
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        logger.info("Пользователь разлогинился");
        return this;
    }

}
