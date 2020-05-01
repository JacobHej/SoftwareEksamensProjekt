Feature: tilfoej tid til aktivitet
  Description: Tilfoej tid til en aktivitet
	Actor: Medarbejder

Scenario: Medarbejder tilfoejer arbejdstimer til aktivitet som har nok budgetteret tid
    Given der er en medarbejder med ID'et "MedarbejderID" og et projekt med ID'et "ProjektID"
    And Projektet "ProjektID" har en aktivitet "AktivitetID"
    When Medarbejderen "MedarbejderID" tilfoejer timer til aktiviteten "AktivitetID"
    Then Medarbejderens timer bliver noteret
    

Scenario: Medarbejder tilfoejer flere timer end der er bugetteret for paa aktiviteten.
	Given der er en medarbejder med ID'et "MedarbejderID" og et projekt med ID'et "ProjektID"
	And Projektet "ProjektID" har en aktivitet "AktivitetID"
	When Medarbejderen "MedarbejderID" tilfoejer for mange timer til aktiviteten "AktivitetID"
	Then Kast exception "Antallet af timer kan ikke registreres - overgaar budgetteret tid!"