package tests;

import org.junit.Before;
import org.junit.Test;

public class DSLFormPageTest extends BaseTest {

    @Before
    public void clickOnAccept(){
        dslFormPage.clickOnAkzepterien();
    }

    //TestCase1
    @Test
    public void check_that_the_DSL_form_page_works_correctly_with_valid_data() {
        dslFormPage.inputInPLZOderOrt("45127 Essen");
        dslFormPage.inputInStrasse("Flachsmarkt");
        dslFormPage.inputInNr("5");
        dslFormPage.clickOnNeinForOngoingContract();
        dslFormPage.clickOnNurInternet();
        dslFormPage.clickOnWLANRouter();
        dslFormPage.clickOnNeinForUnder30();
        dslFormPage.inputPhoneNumber("+49 171 1234567");
        dslFormPage.clickOnSubmit();
        dslFormPage.assertSubmittedSuccessfully();
    }

    //TestCase7
    @Test
    public void check_that_the_return_button_functions_correctly() {
        dslFormPage.clickOnZuruck();
        dslFormPage.assertSubmittedSuccessfully();
    }
}
