package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exceptions.InvalidOptionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DSLFormPage;

public class DSLFormPageStepDefinitions {
    Logger LOGGER = LogManager.getLogger(DSLFormPageStepDefinitions.class);
    DSLFormPage dslFormPage = new DSLFormPage(CucumberHooks.getDriver());
    WebDriverWait wait = CucumberHooks.getWebDriverWait();

    @Given("I say hello world")
    public void i_say_hello_world() {
        System.out.println("hello world!");
    }

    @Given("I click on Akzeptieren")
    public void iClickOnAkzepterien() {
        dslFormPage.clickOnAkzepterien();
        LOGGER.info("hello world");
    }

    @Given("^I type (.*?) in the PLZ oder Ort text field$")
    public void iTypeEssenInThePLZOderOrtTextField(String plzOderOrt) {
        dslFormPage.inputInPLZOderOrt(plzOderOrt);
    }

    @Given("^I type (.*?) in the Zuerst PLZ/Ort eingeben text field$")
    public void andITypeFlachsmarktInTheZuerstPLZOrtEingebenTextField(String strasse) {
        dslFormPage.inputInStrasse(strasse);
    }

    @Given("^I type (\\d+) in the Nr\\. text field$")
    public void iTypeInTheNrTextField(int nr) {
        dslFormPage.inputInNr(String.valueOf(nr));
    }

    @Given("^I pick (Ja|Nein) in the Besteht ein laufender Vertrag radio button options$")
    public void iPickNeinInTheBestehtEinLaufenderVertragRadioButtonOptions(String jaOderNein) throws InvalidOptionException {
        dslFormPage.clickOnJaOderNeinForOngoingContract(jaOderNein);
    }

    @Given("^I pick (nur Internet|Internet mit extra TV-Paket|alle Tarife) in the Welche Tarife suchen Sie radio button options$")
    public void iPickNurInternetAmGunstigstenInTheWelcheTarifeSuchenSieRadioButtonOptions(String tarifeOption) throws InvalidOptionException {
        dslFormPage.clickOnTarifeOption(tarifeOption);
    }


    @Given("^I (select|deselect) Ich brauche einen WLAN Router in the Brauchen Sie einen WLAN Router checkbox$")
    public void iSelectOnIchBraucheEinenWLANRouterInTheBrauchenSieEinenWLANRouterCheckbox(String select) {
        dslFormPage.clickOnWLANRouter();
    }

    @Given("^I pick (Ja|Nein) in the Tarife für junge Leute anzeigen radio button options$")
    public void iPickNeinInTheTarifeFurJungeLeuteAnzeigenRadioButtonOptions(String jaOderNein) throws Throwable {
        dslFormPage.clickOnNeinForUnder30(jaOderNein);
    }

    @Given("^I type (.*?) in the Mobilfunknummer eingeben text field$")
    public void iTypeInTheMobilfunknummerEingebenTextField(String phoneNumber) {
        dslFormPage.inputPhoneNumber(phoneNumber);
    }

    @When("^I click on the zum Vergleichsergebnis button$")
    public void iClickOnTheZumVergleichsergebnisButton() {
        dslFormPage.clickOnSubmit();
    }

    @Then("^the form passes successfully$")
    public void theFormPassesSuccessfully() {
        dslFormPage.assertSubmittedSuccessfully();
    }

    @Then("^Welcher ist Ihr derzeitiger Anbieter appears with six radio button wrappers next to it$")
    public void welcherIstIhrDerzeitigerAnbieterAppearsWithSixRadioButtonWrappersNextToIt() {
        dslFormPage.assertRadioWrappersExist();
    }

    @When("^I click on (.*?) radio wrapper$")
    public void iClickOnRadioWrapper(String provider) throws InvalidOptionException {
        switch (provider) {
            case "Telekom":
                dslFormPage.clickOnProvider(0);
                break;
            case "o2":
                dslFormPage.clickOnProvider(1);
                break;
            case "1&1":
                dslFormPage.clickOnProvider(2);
                break;
            case "Vodafone DSL":
                dslFormPage.clickOnProvider(3);
                break;
            case "Vodafone Kabel Deutschland":
                dslFormPage.clickOnProvider(4);
                break;
            case "Vodafone Unitymedia":
                dslFormPage.clickOnProvider(5);
                break;
            default:
                throw new InvalidOptionException();
        }
    }

    @Then("^the word (.*?) appears in the text field below$")
    public void theWordTelekomAppearsInTheTextFieldBelow(String provider) {
        Assert.assertEquals(dslFormPage.getInputValueFromProviderInput(), provider);
    }

    @When("^I click on the zurück button$")
    public void iClickOnTheZurückButton() {
        dslFormPage.clickOnZuruck();
    }

    @Then("^I get redirected to the following url: (.*?)$")
    public void iGetRedirectedToTheFollowingUrlHttpsWwwCheckDeDsl(String url) {
        wait.until(ExpectedConditions.urlContains(url));
    }

    @Then("^the form doesn’t pass$")
    public void theFormDoesnTPass() throws InterruptedException {
        wait.until(ExpectedConditions.urlToBe("https://www.check24.de/dsl/input2/"));
    }

    @Then("^the PLZ oder Ort text field is highlighted in red$")
    public void thePLZOderOrtTextFieldIsHighlightedInRed() {
        dslFormPage.checkThatPLZOderOrtIsHighlightedInRed();
    }


//    @Then("^a message under PLZ oder Ort text field appears$")
//    public void theAMessageUnderPLZOderOrtTextFieldAppears() {
//        dslFormPage.
//    }
}
