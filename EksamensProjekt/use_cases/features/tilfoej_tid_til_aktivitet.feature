Feature: tilfoej tid til aktivitet
  Description: Tilfoej tid til en aktivitet
	Actor: Medarbejder

Scenario: Medarbejder tilfoejer arbejdstimer til aktivitet som har nok budgetteret tid
    Given der er en medarbejder med ID'et "MADS" og et projekt med navnet "KasseSystem"
    And Projektet "KasseSystem" har en aktivitet "SystemDesign"
    And Medarbejderen "MADS" arbejder paa aktiviteten "SystemDesign"
    And Aktiviteten "SystemDesign" har mere end 2 ubrugte budgetterede timer
    When Medarbejderen "MADS" tilfoejer 2 timer til aktiviteten "SystemDesign"
    Then Medarbejderens timer 2 er blevet noteret
    

#Scenario: Medarbejder tilfoejer flere timer end der er bugetteret for paa aktiviteten.
#	Given der er en medarbejder med ID'et "MedarbejderID" og et projekt med navnet "ProjektNavn"
#	And Projektet "ProjektNavn" har en aktivitet "AktivitetID"
#	When Medarbejderen "MedarbejderID" tilfoejer for mange timer til aktiviteten "AktivitetID"
#	Then brugeren faar "exception"