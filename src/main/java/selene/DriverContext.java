package selene;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public interface DriverContext {

    <T> T getCapability(String name, T defaultValue);

    WebDriver driver();

    /* todo: should we add?
     *   <T> T getCapability(String name)
     */
}
