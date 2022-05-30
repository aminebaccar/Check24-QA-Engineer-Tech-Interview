package steps;

import cucumber.api.Result;
import cucumber.api.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DSLFormPage;
import util.JunitListener;
import util.PropertyReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberHooks {

    public static PropertyReader propertyReader;
    private static Logger LOGGER = null;
    @Rule
    public JunitListener junitListener;
    public static WebDriver driver;
    public DSLFormPage dslFormPage;

    public static WebDriverWait webDriverWait;

    public static Scenario scenario;

    public CucumberHooks() {
        junitListener = new JunitListener();
        propertyReader = new PropertyReader();
        LOGGER = LogManager.getLogger(CucumberHooks.class);
    }

    @Before
    public void setUp(Scenario scenario) {
        System.setProperty("log4j.configurationFile", "../../resources/log4j2.xml");
        CucumberHooks.scenario = scenario;
        LOGGER.info("SetUp");
        setDriver();
        int implicitWaitInSeconds = Integer.parseInt(propertyReader.readProperty("implicitWaitInSeconds"));
        webDriverWait = new WebDriverWait(driver, implicitWaitInSeconds);
        junitListener.setDriver(driver);
        dslFormPage = new DSLFormPage(driver);
        loadUrl();
    }

    @After
    public void tearDown(Scenario scenario) {
        LOGGER.info("TearDown");
        junitListener.finished(scenario);
        driver.close();
    }

    public void setDriver() {
        long implicitWait = Integer.parseInt(propertyReader.readProperty("implicitWaitInSeconds"));
        System.setProperty("webdriver.chrome.driver", propertyReader.readProperty("chromeDriver"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void loadUrl() {
        URL baseUrl;
        HttpURLConnection connection = null;
        try {
            baseUrl = new URL(propertyReader.readProperty("url"));
            connection = (HttpURLConnection) baseUrl.openConnection();
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                LOGGER.error("Unable to connect to the url connection");
                LOGGER.error("Response Code " + connection.getResponseCode());
                LOGGER.error("Response Message " + connection.getResponseMessage());
            }
            driver.get(propertyReader.readProperty("url"));
        } catch (IOException e) {
            LOGGER.error("URL connection error");
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }
}