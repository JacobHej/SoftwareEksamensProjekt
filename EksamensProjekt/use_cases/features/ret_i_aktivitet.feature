Feature: Ret i aktivitet
  Description: Ret i aktivitetens data
	Actor: Projekt-Leder
	
Scenario: Projektleder oensker at rette i data for eksisterende aktivitet
	Given der er et projekt "Projekt1" med lederen "ledr"
    And Projektet "Projekt1" har aktiviteten "aktivitet1"
    When aktivitetens navn aendres til "Refactor kode"
    Then aktivitetens navn er nu "Refactor kode"
    When aktivitetens budgetterede tid aendres til 10
    Then aktivitetens budgetterede tid er nu 10
    When aktivitetens start- og slut-tidspunkter aendres til hhv. uge 6 og 10
	Then aktivitetens start- og slut-tidspunkter er nu hhv. 6 og 10

Scenario: Medarbejder retterantallet af timer han/hun har registreret paa en aktivitet
	Given Vi kigger paa projektet "Projekt1"
	And der er en medarbejder "AVS"
	And "AVS" har registreret 10 timer paa aktiviteten "Refactor kode"
	When "AVS" retter tiden som han har brugt i aktiviteten til 8
	Then "AVS" har kun 8 timer registreret i aktiviteten "Refactor kode"
	