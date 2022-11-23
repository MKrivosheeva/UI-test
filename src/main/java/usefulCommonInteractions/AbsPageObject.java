package usefulCommonInteractions;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;


public abstract class AbsPageObject {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected Faker enFaker = new Faker(new Locale("en-GB"));
    protected Faker ruFaker = new Faker(new Locale("ru-RU"));
    protected Faker dateFaker = new Faker();
    public org.apache.logging.log4j.Logger logger = LogManager.getLogger(AbsPageObject.class);


    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    protected AbsPageObject() {
    }

    public WebDriver getDriver() {
        return driver;
    }
    public Actions getActions() {
        return actions;
    }

}






