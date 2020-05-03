Feature: Ret i projekt
  Description: Ret i projektets data
	Actor: Projekt-Leder
	
Scenario: Projektleder oensker at rette i data for sit projekt
	Given der er et projekt "GammeltNavn" med lederen "leder"
	And Projektet "GammeltNavn" har aktiviteten "aktivitetID"
    When Projektets navn aendres fra "GammeltNavn" til "Banksystem"
    Then Projektets navn er "Banksystem"
    When Projektet faar en ny projektleder med navnet "HACR"
    Then Projektets projektleder har navnet "HACR"
    When Projektet faar sat sin starttid til uge 2 aar 2021
    Then Projektets starttid er uge 2 aar 2021
    
Scenario: Projektleder oensker at slette en aktivitet som ikke har timer registreret
	Given der er et projekt "Hospitalsovervaagning" med lederen "EAL"
    And Projektet "Hospitalsovervaagning" har aktiviteten "Udvikling af brugergraenseflade"
    When Projektlederen sletter aktiviteten "Udvikling af brugergraenseflade" fra projektet "Hospitalsovervaagning"
    Then Projektet har ikke laengere aktiviteten


#Scenario: Projektleder proever at slette en aktivitet som har timer registreret
#	Given der er et projekt "ProjektNavn" med lederen "leder"
#    And Projektet "ProjektNavn" har aktiviteten "aktivitetID"
#    And aktiviteten "aktivitetID" har 5 timer registreret
#    When Projektlederen sletter aktiviteten "aktivitetID"
#    Then aktiviteten "aktivitetID" er ikke slettet
#    And Kast exception "exception"