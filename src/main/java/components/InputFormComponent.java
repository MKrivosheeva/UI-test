package components;

import data.textFieldsLocators.InputFieldData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputFormComponent extends AbsBaseComponent {

    public InputFormComponent(WebDriver driver) {
        super(driver);
    }

    public InputFormComponent loadImage(String imageFieldLocator, String imagePath, String confirmButtonLocator) {
        String path = System.getProperty("user.dir") + imagePath;
        ((JavascriptExecutor) driver)
                .executeScript("document.querySelector('input[type=file]').scrollIntoView();");
        driver.findElement(By.cssSelector(imageFieldLocator)).sendKeys(path);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(confirmButtonLocator))));
        driver.findElement(By.cssSelector(confirmButtonLocator)).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(confirmButtonLocator))));
        return this;
    }

    public InputFormComponent fillTheDateField(WebElement dateField, Date keys) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(keys);
        wait.until(ExpectedConditions.visibilityOf(dateField));
        dateField.clear();
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);
        return this;
    }

    public InputFormComponent fillInputFields(InputFieldData field, String value) {
        String selector = String.format("[name='%s']", field.getAttributeValue());
        driver.findElement(By.cssSelector(selector)).clear();
        driver.findElement(By.cssSelector(selector)).sendKeys(value);
        return this;
    }

    public InputFormComponent assertInputFields(InputFieldData field, String value) {
        String selector = String.format("[name='%s']", field.getAttributeValue());
        Assertions.assertEquals(value, driver.findElement(By.cssSelector(selector)).getAttribute("value"), "Поле " + field.getFieldName() + " заполнено некорректно");
        return this;
    }

    public InputFormComponent popupInteraction(WebElement popupFieldName, By listOfValuesLocator, String value) {
        popupFieldName.click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(value)))).click();
        wait.until((ExpectedConditions.attributeContains(listOfValuesLocator, "class", "hide")));
        return this;
    }


    public InputFormComponent radioButtonInteraction(String radioButtonFalseSelector, String radiobuttonTrueSelector, Boolean value) {
        String makeVisible;
        if (value) {
            makeVisible = String.format("document.querySelector(\"%s\").style.display = 'block';", radiobuttonTrueSelector);
            ((JavascriptExecutor) driver).executeScript(makeVisible);
            if (!driver.findElement(By.cssSelector(radiobuttonTrueSelector)).isSelected()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(radiobuttonTrueSelector)));
                wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.cssSelector(radiobuttonTrueSelector))));
            }
        } else {
            makeVisible = String.format("document.querySelector(\"%s\").style.display = 'block';", radioButtonFalseSelector);
            ((JavascriptExecutor) driver).executeScript(makeVisible);
            if (!driver.findElement(By.cssSelector(radioButtonFalseSelector)).isSelected()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(radioButtonFalseSelector)));
                wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.cssSelector(radioButtonFalseSelector))));
            }
        }
        return this;
    }

    public InputFormComponent assertRadioButtonChoice(Boolean choice, String radiobuttonTrueSelector, String radioButtonFalseSelector) {
        if (choice) {
            Assertions.assertTrue(driver.findElement(By.cssSelector(radiobuttonTrueSelector)).isSelected(), "Радиокнопка выбрана некорректно");
        } else {
            Assertions.assertTrue(driver.findElement(By.cssSelector(radioButtonFalseSelector)).isSelected(), "Радиокнопка выбрана некорректно");
        }

        return this;
    }

    public InputFormComponent checkboxInteraction(String checkboxSelector, Boolean choice) {
        String makeVisible = String.format("document.querySelector(\"%s\").style.display = 'block';", checkboxSelector);
        ((JavascriptExecutor) driver).executeScript(makeVisible);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(checkboxSelector))));
        if (choice.equals(true)) {
            if (!driver.findElement(By.cssSelector(checkboxSelector)).isSelected()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(checkboxSelector)));
                wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.cssSelector(checkboxSelector)), true));
            }
        } else {
            if (driver.findElement(By.cssSelector(checkboxSelector)).isSelected()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(checkboxSelector)));
                wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.cssSelector(checkboxSelector)), false));
            }
        }
        return this;
    }

    public InputFormComponent assertCheckboxChoice(String checkboxSelector, Boolean choice) {
        if (choice.equals(true)) {
            Assertions.assertTrue(driver.findElement(By.cssSelector(checkboxSelector)).isSelected(), "Чек-бокс в некорректном состоянии");
        } else {
            Assertions.assertFalse(driver.findElement(By.cssSelector(checkboxSelector)).isSelected(), "Чек-бокс в некорректном состоянии");
            if (driver.findElement(By.cssSelector(checkboxSelector)).isSelected()) {
            }
        }
        return this;
    }
}








