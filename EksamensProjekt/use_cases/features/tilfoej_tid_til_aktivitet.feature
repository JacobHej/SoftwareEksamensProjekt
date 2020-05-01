Feature: tilfoej tid til aktivitet
  Description: Tilfoej tid til en aktivitet
	Actor: Medarbejder

Scenario: Medarbejder tilfoejer arbejdstimer til aktivitet som har nok budgetteret tid
    Given der er en medarbejder med ID'et "MedarbejderID" og et projekt med navnet "ProjektNavn"
    And Projektet "ProjektNavn" har en aktivitet "AktivitetID"
    And Medarbejderen "MedarbejderID" arbejder paa aktiviteten "AktivitetID"
    And Aktiviteten "AktivitetID" har mere end 2 ubrugte budgetterede timer
    When Medarbejderen "MedarbejderID" tilfoejer 2 timer til aktiviteten "AktivitetID"
    Then Medarbejderens timer er blevet noteret
    

#Scenario: Medarbejder tilfoejer flere timer end der er bugetteret for paa aktiviteten.
#	Given der er en medarbejder med ID'et "MedarbejderID" og et projekt med ID'et "ProjektID"
#	And Projektet "ProjektID" har en aktivitet "AktivitetID"
#	When Medarbejderen "MedarbejderID" tilfoejer for mange timer til aktiviteten "AktivitetID"
#	Then Kast exception "Antallet af timer kan ikke registreres - overgaar budgetteret tid!"