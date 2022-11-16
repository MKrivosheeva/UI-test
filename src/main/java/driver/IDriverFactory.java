package driver;

import exception.DriverNotSupportedException;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
    WebDriver getDriver() throws DriverNotSupportedException;
}

