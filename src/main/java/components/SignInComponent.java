package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInComponent extends AbsBaseComponent {

    public SignInComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[placeholder='Электронная почта'][type='text']")
    WebElement loginField;
    @FindBy(css ="input[type=password]")
    WebElement pswdField;
    @FindBy(css ="button[class$='new-button_md'][type='submit']")
    WebElement enterButton;

   public SignInComponent signIn(String login, String password) {
   wait.until(ExpectedConditions.elementToBeClickable(loginField));
   loginField.sendKeys(login);
   pswdField.sendKeys(password);
   enterButton.click();
   wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[class$='log-reg-container'][data-modal-id='new-log-reg']"))));
   return this;
   }
}


