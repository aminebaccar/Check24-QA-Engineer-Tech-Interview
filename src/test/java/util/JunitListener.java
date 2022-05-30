package util;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class JunitListener extends TestWatcher {

    private WebDriver driver;
    private String screenshotDestinationFolder = "target/screenshot/";


    public void finished(Scenario scenario) {
        System.out.println("Test completed " + scenario.getName());
        takeScreenShot(scenario.getName());
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private void takeScreenShot(String scenarioName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(screenshotDestinationFolder + scenarioName + ".png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}