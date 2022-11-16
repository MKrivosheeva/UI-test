package exception;

public class DriverNotSupportedException extends Throwable {
    public DriverNotSupportedException(String browser) {

        super(String.format("Browser %s not supported", browser));
    }
}
