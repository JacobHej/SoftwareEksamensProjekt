Feature: Ret i aktivitet
  Description: Ret i aktivitetens data
	Actor: Projekt-Leder
	
Scenario: Projektleder oensker at rette i data for eksisterende aktivitet
	Given der er et projekt "ProjektNavn" med lederen "leder"
    And Projektet "ProjektNavn" har aktiviteten "aktivitetID"
    When aktivitetens navn aendres til "Refactor kode"
    Then aktivitetens navn er nu "Refactor kode"
    When aktivitetens budgetterede tid aendres til 10
    Then aktivitetens budgetterede tid er nu 10
    When aktivitetens start- og slut-tidspunkter aendres til hhv. uge 6 og 10
	Then aktivitetens start- og slut-tidspunkter er nu hhv. 6 og 10