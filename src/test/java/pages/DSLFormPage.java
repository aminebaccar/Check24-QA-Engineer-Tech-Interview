package pages;

import exceptions.InvalidOptionException;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.CucumberHooks;
import util.JavaScriptEvents;

import java.util.List;

public class DSLFormPage {

    @FindBy(xpath = "//a[text()='Akzeptieren']")
    private WebElement acceptButton;

    @FindBy(xpath = "//input[@placeholder='PLZ oder Ort']")
    private WebElement plzOderOrt;

    @FindBy(xpath = "//input[@placeholder='Straße']")
    private WebElement strasse;

    @FindBy(xpath = "//input[@placeholder='Nr.']")
    private WebElement nr;

    @FindBy(xpath = "(//label[contains(text(), '  Nein  ')])[1]")
    private WebElement ongoingContractNein;

    @FindBy(xpath = "(//label[contains(text(), '  Ja  ')])[1]")
    private WebElement ongoingContractJa;

    @FindBy(xpath = "//span[text()='nur Internet ']")
    private WebElement nurInternet;

    @FindBy(xpath = "//label[contains(text(),'Internet mit extra TV-Paket')]")
    private WebElement internetMitExtraTvPaket;

    @FindBy(xpath = "//label[contains(text(),'alle Tarife')]")
    private WebElement alleTarife;

    @FindBy(xpath = "//input[@type='checkbox' and @class='tko-checkbox']")
    private WebElement wlanRouter;

    @FindBy(xpath = "(//label[contains(text(), '  Nein  ')])[2]")
    private WebElement under30Nein;

    @FindBy(xpath = "(//label[contains(text(), '  Ja  ')])[2]")
    private WebElement under30Ja;

    @FindBy(xpath = "//input[@placeholder='Mobilfunknummer eingeben']")
    private WebElement phoneNumber;

    @FindBy(xpath = "//a[@href='https://www.check24.de/dsl/']//*[contains(text(), 'zurück')]")
    private WebElement zuruck;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    @FindBy(xpath = "//label/img")
    private List<WebElement> radioButtonWrappers;
    @FindBy(xpath = "//input[@placeholder='Anbieter eingeben/auswählen']")
    private WebElement providerInput;

    private WebDriver webDriver;
    private Actions actions;

    public DSLFormPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void clickOnAkzepterien() {
        acceptButton.click();
    }

    public void inputInPLZOderOrt(String plzOderOrt) {
        this.plzOderOrt.sendKeys(plzOderOrt);
        this.plzOderOrt.sendKeys(Keys.RETURN);
    }

    public void inputInStrasse(String strasse) {
        this.strasse.sendKeys(strasse);
        this.strasse.sendKeys(Keys.RETURN);
    }

    public void inputInNr(String nr) {
        this.nr.sendKeys(nr);
    }

    public void clickOnJaOderNeinForOngoingContract(String jaOderNein) throws InvalidOptionException {
        if ("Ja".equalsIgnoreCase(jaOderNein)) ongoingContractJa.click();
        else if ("Nein".equalsIgnoreCase(jaOderNein)) ongoingContractNein.click();
        else throw new InvalidOptionException();
    }

    public void clickOnTarifeOption(String tarifeOption) throws InvalidOptionException {
        if (tarifeOption.toLowerCase().contains("nur internet"))
            nurInternet.click();
        else if (tarifeOption.toLowerCase().contains("internet mit extra tv-paket"))
            internetMitExtraTvPaket.click();
        else if (tarifeOption.toLowerCase().contains("alle tarife"))
            alleTarife.click();
        else
            throw new InvalidOptionException();
    }

    public void clickOnWLANRouter() {
        JavaScriptEvents.clickByJavaScript(wlanRouter);
    }

    public void clickOnNeinForUnder30(String jaOderNein) throws InvalidOptionException {
        if ("Ja".equalsIgnoreCase(jaOderNein)) {
            JavaScriptEvents.clickByJavaScript(under30Nein);
        } else if ("Nein".equalsIgnoreCase(jaOderNein)) {
            JavaScriptEvents.clickByJavaScript(under30Nein);
        } else
            throw new InvalidOptionException();
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
        WebDriverWait wait = CucumberHooks.getWebDriverWait();
        wait.until(ExpectedConditions.urlContains("https://www.check24.de/dsl/input2/ergebnisliste"));
    }

    public void clickOnZuruck() {
        zuruck.click();
    }

    public void assertRadioWrappersExist() {
        Assert.assertEquals(6, radioButtonWrappers.size());
    }

    public void clickOnProvider(int order) {
        actions.moveToElement(radioButtonWrappers.get(order)).click().build().perform();
    }

    public String getInputValueFromProviderInput() {
        return providerInput.getAttribute("value");
    }

    public void checkThatPLZOderOrtIsHighlightedInRed() {
        assertInputHighlightedInRed(plzOderOrt);
    }

    public void assertInputHighlightedInRed(WebElement input) {
        Assert.assertEquals("tko-textinput tko-error-field", input.getAttribute("class"));
    }
}


