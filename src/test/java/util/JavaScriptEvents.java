package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.CucumberHooks;

public class JavaScriptEvents {

    public static void clickByJavaScript(WebElement webElement) {
        WebDriver driver = CucumberHooks.getDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

}
