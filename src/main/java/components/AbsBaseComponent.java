package components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.AbsPageObject;

public abstract class AbsBaseComponent extends AbsPageObject {

    public AbsBaseComponent(WebDriver driver) {
        super(driver);
    }


    protected WebDriverWait wait = new WebDriverWait(driver, 5);
    protected JavascriptExecutor js = (JavascriptExecutor) driver;

//    public  void fillTextField(By fieldLocator, String keys) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
//        driver.findElement(fieldLocator).clear();
//        driver.findElement(fieldLocator).sendKeys(keys);
//    }

//     public void clickSimpleButton(By cssButtonSelector)  {
//     actions.moveToElement(driver.findElement(cssButtonSelector));//убрать
//     wait.until(ExpectedConditions
//                       .elementToBeClickable(cssButtonSelector)).click();//убрать в нужный компонент
//    }
}






