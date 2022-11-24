package components;

import org.openqa.selenium.By;
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
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[class$='log-reg-container'][data-modal-id='new-log-reg']"))));
        SignInComponent authorizationWindow = new SignInComponent(driver);
    return authorizationWindow;
    }

}
