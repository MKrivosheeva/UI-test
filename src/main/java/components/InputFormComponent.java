package components;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InputFormComponent extends AbsBaseComponent {

    public InputFormComponent(WebDriver driver) {
        super(driver);
    }

    public void loadImage (String imageFieldLocator, String imagePath,String confirmButtonLocator) {
    String path =System.getProperty("user.dir")+imagePath;
    ((JavascriptExecutor) driver)
                .executeScript("document.querySelector('input[type=file]').scrollIntoView();");
     driver.findElement(By.cssSelector(imageFieldLocator)).sendKeys(path);
     wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(confirmButtonLocator))));
     driver.findElement(By.cssSelector(confirmButtonLocator)).click();
     wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(confirmButtonLocator))));
    }

    public void fillTheDateField(By fieldLocator, Date keys) {
        WebElement dateField;
        SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(keys);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        dateField = driver.findElement(fieldLocator);
        dateField.clear();
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);
    }

    public void popupInteraction(By popupFieldName, String value) { //тут надо изменить ожидание на реакцию
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(popupFieldName));
       ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(popupFieldName));
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(value)));
       ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(value)));

}

    public void radioButtonInteraction(String radioButtonFalseSelector, String radiobuttonTrueSelector, Boolean value) {
        String makeVisible = "";
        ((JavascriptExecutor) driver).executeScript(makeVisible);
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
    }

    public void checkboxSelect (String checkboxSelector) {  //добавить булевское значение тру/фолз
        String makeVisible = "document.querySelector(\"" + checkboxSelector +"\").style.display = 'block';";
        ((JavascriptExecutor) driver).executeScript(makeVisible);
        if (!driver.findElement(By.cssSelector(checkboxSelector)).isSelected()) {
            driver.findElement(By.cssSelector(checkboxSelector)).click();
            wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.cssSelector(checkboxSelector)), true));
      //элементтубиселектед
        }
    }
   public void checkboxUnSelect (String checkboxSelector) {
     String makeVisible = "document.querySelector(\"" + checkboxSelector +"\").style.display = 'block';";
      ((JavascriptExecutor) driver).executeScript(makeVisible);
      if (driver.findElement(By.cssSelector(checkboxSelector)).isSelected()) {
            driver.findElement(By.cssSelector(checkboxSelector)).click();
            wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.cssSelector(checkboxSelector)), false));
        }
    }

//    public void radioButton (By selector, String value) {
//        if (!driver.findElement(selector).isSelected()) {
//            driver.findElement(selector).click();
//        }
//
//        if(checkBox.getAttribute("checked") != null) // if Checked
//            checkBox.click();

        // Boolean   isChecked = e.findElement(By.tagName("input")).getAttribute("checked").equals("true");
//// for all checked checkboxes
//        driver.findElements(By.cssSelector("input:checked[type='checkbox']"));
//// for all notchecked checkboxes
//        driver.findElements(By.cssSelector("input:not(:checked)[type='checkbox']"));
//        if(!driver.findElement(By.xpath("//input[@type='checkbox' and @name='<name>']")).isSelected())
//        {
//            driver.findElement(By.xpath("//input[@type='checkbox' and @name= '<name>']")).click();
//        }




    public void checkSavedData () {


    }


}



//    public void clickSimpleButton(By cssButtonSelector)  {
//
//        wait.until(ExpectedConditions
//                        .elementToBeClickable(cssButtonSelector))
//                .click();
//    }
//  public void fieldIsTheSame (By fieldLocator, String controlValue) {
//      textField = driver.findElement(fieldLocator);
//      String currentValue = textField.getText();
//      Assertions.assertEquals(controlValue, currentValue);
//
//  }




