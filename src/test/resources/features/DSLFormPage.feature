Feature: DSL Form Page

  Background:
    Given I click on Akzeptieren

  @TestCase1
  Scenario: Check that the DSL form page works correctly with valid data
    Given I type 45127 Essen in the PLZ oder Ort text field
    And I type Flachsmarkt in the Zuerst PLZ/Ort eingeben text field
    And I type 5 in the Nr. text field
    And I pick Nein in the Besteht ein laufender Vertrag radio button options
    And I pick nur Internet in the Welche Tarife suchen Sie radio button options
    And I select Ich brauche einen WLAN Router in the Brauchen Sie einen WLAN Router checkbox
    And I pick Nein in the Tarife für junge Leute anzeigen radio button options
    And I type +49 171 1234567 in the Mobilfunknummer eingeben text field
    When I click on the zum Vergleichsergebnis button
    Then the form passes successfully

  @TestCase2
  Scenario: Check that the current provider options appear and function correctly
    When I pick Ja in the Besteht ein laufender Vertrag radio button options
    Then Welcher ist Ihr derzeitiger Anbieter appears with six radio button wrappers next to it
    When I click on Telekom radio wrapper
    Then the word Telekom appears in the text field below
    When I click on o2 radio wrapper
    Then the word o2 appears in the text field below
    When I click on 1&1 radio wrapper
    Then the word 1&1 Telecom GmbH appears in the text field below
    When I click on Vodafone DSL radio wrapper
    Then the word Vodafone appears in the text field below
    When I click on Vodafone Kabel Deutschland radio wrapper
    Then the word Vodafone Kabel Deutschland appears in the text field below
    When I click on Vodafone Unitymedia radio wrapper
    Then the word Vodafone appears in the text field below

  @TestCase7
  Scenario: Check that the return button functions correctly
    When I click on the zurück button
    Then I get redirected to the following url: https://www.check24.de/dsl/

  @TestCase5
  Scenario: Check that the DSL form page doesn’t pass when provided with wrong PLZ oder Ort & Zuerst PLZ Ort eingeben
    And I type 4040 Sidi Bou Ali in the PLZ oder Ort text field
    And I type Sousse in the Zuerst PLZ/Ort eingeben text field
    And I type 5 in the Nr. text field
    And I pick Nein in the Besteht ein laufender Vertrag radio button options
    And I pick nur Internet in the Welche Tarife suchen Sie radio button options
    And I select Ich brauche einen WLAN Router in the Brauchen Sie einen WLAN Router checkbox
    And I pick Nein in the Tarife für junge Leute anzeigen radio button options
    And I type +49 171 1234567 in the Mobilfunknummer eingeben text field
    When I click on the zum Vergleichsergebnis button
    Then the form doesn’t pass
    And the PLZ oder Ort text field is highlighted in red





