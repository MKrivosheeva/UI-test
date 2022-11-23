package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import usefulCommonInteractions.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {

    protected final String BASE_URL = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {

        super(driver);
    }

    public void open(String path, WebDriver driver) {
        driver.get(BASE_URL + path);
        wait.until(ExpectedConditions.urlToBe(BASE_URL + path));
    }

    public void close() {
        if (this.driver != null) {
            this.driver.close();
        }
    }
}