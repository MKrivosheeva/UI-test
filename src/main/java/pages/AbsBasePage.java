package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import usefulCommonInteractions.AbsPageObject;
import java.util.Locale;


public abstract class AbsBasePage extends AbsPageObject {

    protected final String BASE_URL = System.getProperty("base.url");
    protected Faker enFaker = new Faker(new Locale("en-GB"));
    protected Faker ruFaker = new Faker(new Locale("ru-RU"));
    protected Faker dateFaker = new Faker();

    public AbsBasePage(WebDriver driver) {

        super(driver);
    }

    public void open(String path) {
        driver.get(BASE_URL + path);
        wait.until(ExpectedConditions.urlToBe(BASE_URL + path));
    }

    public void close() {
        if (this.driver != null) {
            this.driver.close();
        }
    }
}