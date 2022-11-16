package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInComponent extends AbsBaseComponent {

    public SignInComponent(WebDriver driver) {
        super(driver);
    }

    public void signIn(String login, String password) {
        By signInButtonSelector = By.cssSelector("button[data-modal-id=new-log-reg]");
        By loginField = By.cssSelector("input[placeholder='Электронная почта'][type='text']");
        By pswdField = By.cssSelector("input[type=password]");
        By enterButton = By.cssSelector("button[class$='new-button_md'][type='submit']");
        clickSimpleButton(signInButtonSelector);
        fillTextField(loginField, login);
        fillTextField(pswdField, password);
        clickSimpleButton(enterButton);
    }

}
