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
   wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[class='modal-container new-log-reg-container']"))));

   return this;
   }
}
//    public SignInComponent signIn(String login, String password) { //вернуть объект модальное окно авторизации
//        //создать компонент с хэдером
//        //в нём кликать по кнопке
//        //метод возвращает объект модалки
//        //после клика методы ввода пароля в модалке, проверить, что закрылось
//        //после входа надо вернуть объект модального окно
//        //открыть страницу, добавить класс для страницы профиля (есть)
//        //метод на абстрактной странице, который закрывает сессию браузера
//        //и в абс.метод открыть
//
//
//       // By signInButtonSelector = By.cssSelector("button[data-modal-id=new-log-reg]"); //
//        By loginField = By.cssSelector("input[placeholder='Электронная почта'][type='text']");
//        By pswdField = By.cssSelector("input[type=password]");
//        By enterButton = By.cssSelector("button[class$='new-button_md'][type='submit']");
//       // clickSimpleButton(signInButtonSelector);
//        fillTextField(loginField, login);
//        fillTextField(pswdField, password);
//        clickSimpleButton(enterButton);
//     return this;
//    }

