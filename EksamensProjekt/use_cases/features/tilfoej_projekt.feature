Feature: tilfoej projekt
  Description: Lav et nyt projekt
	Actor: Medarbejder

Scenario: Medarbejder opretter nyt projekt
    Given der er en medarbejder med ID'et "MedarbejderID"
    When Medarbejderen opretter et projekt med ID'et "ProjektID"
    Then Der findes et nyt projekt med ID'et "ProjektID"