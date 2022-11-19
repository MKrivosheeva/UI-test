package components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.BooleanSupplier;

public class ProfileDropDownComponent extends AbsBaseComponent {

    public ProfileDropDownComponent(WebDriver driver) {
        super(driver);
    }

    public ProfileDropDownComponent unSign() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String exitSelector = "a[title='Выход']";
        String profileMenuSelector = "[class$=username]";
        WebElement profileMenu = driver.findElement(By.cssSelector(profileMenuSelector));
       // actions.moveToElement(profileMenu).click();
        js.executeScript("arguments[0].click();", profileMenu);
        js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(exitSelector)));
//        actions.moveToElement(driver.findElement(By.cssSelector(exitSelector))).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class='sc-9a4spb-0 boaxIc']"))));
        logger.info("Пользователь разлогинился");
    return this;
    }
}
