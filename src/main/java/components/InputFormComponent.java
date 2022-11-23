package components;

import data.textFieldsLocators.InputFieldData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class InputFormComponent extends AbsBaseComponent {


    public InputFormComponent(WebDriver driver) {
        super(driver);
    }

    public InputFormComponent loadImage(WebElement imageField, String imagePath, WebElement confirmButton) {
        String path = System.getProperty("user.dir") + imagePath;
        imageField.sendKeys(path);
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
        wait.until(ExpectedConditions.invisibilityOf(confirmButton));
        return this;
    }

    public InputFormComponent fillTheDateField(WebElement dateField, Date keys) {
        LocalDate thisDate = keys.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(thisDate);
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

    public InputFormComponent radioButtonInteraction(WebElement trueButton, WebElement falseButton, Boolean value) {
        if (trueButton.isSelected()) {
            if (value.equals(false))
                driver.findElement(By.xpath("//input[@type='radio'][@value='False']/..")).click();
            return this;
        }
        if (falseButton.isSelected()) {
            if (value.equals(true))
                driver.findElement(By.xpath("//input[@type='radio'][@value='True']/..")).click();
            return this;
        }
        return this;
    }

    public InputFormComponent assertRadioButtonChoice(WebElement trueButton, WebElement falseButton, Boolean value) {
        if (value) {
            Assertions.assertTrue(trueButton.isSelected(), "Радиокнопка выбрана некорректно");
        } else {
            Assertions.assertTrue(falseButton.isSelected(), "Радиокнопка выбрана некорректно");
        }

        return this;
    }

    public InputFormComponent checkboxInteraction(WebElement checkbox, Boolean choice, String checkBoxName) {
        String checkBoxLabelSelector = String.format("//input[@title='%s']/..", checkBoxName);
        WebElement checkBoxLabel = driver.findElement(By.xpath(checkBoxLabelSelector));
        if (checkbox.isSelected()) {
            if (choice.equals(false)) {
                checkBoxLabel.click();
                return this;
            }
        }
        if (!checkbox.isSelected()) {
            if (choice.equals(true)) {
                checkBoxLabel.click();
                return this;
            }
        }
        return this;
    }

    public InputFormComponent assertCheckboxChoice(WebElement checkbox, Boolean choice) {
        if (choice.equals(true)) {
            Assertions.assertTrue((checkbox).isSelected(), "Чек-бокс в некорректном состоянии");
        } else {
            Assertions.assertFalse((checkbox).isSelected(), "Чек-бокс в некорректном состоянии");
         }
        return this;
    }
}








