package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DSLFormPage;
import util.JunitListener;
import util.PropertyReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static PropertyReader propertyReader;
    private static Logger LOGGER = null;
    @Rule
    public JunitListener junitListener;
    public WebDriver driver;
    public DSLFormPage dslFormPage;

    public BaseTest() {

        junitListener = new JunitListener();
        propertyReader = new PropertyReader();
        LOGGER = LogManager.getLogger(BaseTest.class);
    }

    @Before
    public void setUp() {
        LOGGER.info("SetUp");
        setDriver();
        junitListener.setDriver(driver);
        dslFormPage = new DSLFormPage(driver);
        loadUrl();
    }

    @After
    public void tearDown() {
        LOGGER.info("TearDown");
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
}
