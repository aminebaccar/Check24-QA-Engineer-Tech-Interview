package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class DSLFormPage {

    @FindBy(xpath = "//a[text()='Akzeptieren']")
    private WebElement accept_button;

    @FindBy(xpath = "//input[@placeholder='PLZ oder Ort']")
    private WebElement plz_oder_ort;

    @FindBy(xpath = "//input[@placeholder='Straße']")
    private WebElement strasse;

    @FindBy(xpath = "//input[@placeholder='Nr.']")
    private WebElement nr;

    @FindBy(xpath = "(//label[contains(text(), '  Nein  ')])[1]")
    private WebElement ongoingContractNein;

    @FindBy(xpath = "//span[text()='nur Internet ']")
    private WebElement nurInternet;

    @FindBy(xpath = "//input[@type='checkbox' and @class='tko-checkbox']")
    private WebElement wlanRouter;

    @FindBy(xpath = "(//label[contains(text(), '  Nein  ')])[2]")
    private WebElement under30Nein;

    @FindBy(xpath = "//input[@placeholder='Mobilfunknummer eingeben']")
    private WebElement phoneNumber;

    @FindBy(xpath = "//a[@href='https://www.check24.de/dsl/']//*[contains(text(), 'zurück')]")
    private WebElement zuruck;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    private WebDriver webDriver;
    private Actions actions;

    public DSLFormPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void clickOnAkzepterien() {
        accept_button.click();
    }

    public void inputInPLZOderOrt(String plzOderOrt) {
        plz_oder_ort.sendKeys(plzOderOrt);
        plz_oder_ort.sendKeys(Keys.RETURN);
    }

    public void inputInStrasse(String strasse) {
        this.strasse.sendKeys(strasse);
        this.strasse.sendKeys(Keys.RETURN);
    }

    public void inputInNr(String nr) {
        this.nr.sendKeys(nr);
    }

    public void clickOnNeinForOngoingContract() {
        ongoingContractNein.click();
    }

    public void clickOnNurInternet() {
        nurInternet.click();
    }

    public void clickOnWLANRouter() {
        actions.moveToElement(wlanRouter).click().build().perform();
    }

    public void clickOnNeinForUnder30() {
        actions.moveToElement(under30Nein).click().build().perform();
    }

    public void inputPhoneNumber(String phoneNumber) {
        this.phoneNumber.sendKeys(phoneNumber);
    }

    public void clickOnSubmit() {
        actions.moveToElement(submit).click().build().perform();
    }

    public void clickOnReturn() {
        actions.moveToElement(submit).click().build().perform();
    }

    public void assertSubmittedSuccessfully() {
        assertNotSame("https://www.check24.de/dsl/input2/", webDriver.getCurrentUrl());
    }

    public void clickOnZuruck(){
        zuruck.click();
    }
}
