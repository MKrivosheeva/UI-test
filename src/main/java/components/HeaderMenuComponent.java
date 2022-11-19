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
    WebElement signInButtonSelector;

    public SignInComponent initSignIn() {
        signInButtonSelector.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[class='modal-container new-log-reg-container']"))));
        SignInComponent authorizationWindow = new SignInComponent(driver);

    return authorizationWindow;
    }


}
