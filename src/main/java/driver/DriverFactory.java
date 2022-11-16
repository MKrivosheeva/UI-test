package driver;

import exception.DriverNotSupportedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverFactory implements IDriverFactory {

   private String browserType = System.getProperty("browser").toLowerCase();

   @Override
   public WebDriver getDriver() throws DriverNotSupportedException {
      switch (this.browserType) {
         case "chrome": {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("disable-popup-blocking");
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            return driver;
         }
         default:
            throw new DriverNotSupportedException(this.browserType);
      }
   }
}