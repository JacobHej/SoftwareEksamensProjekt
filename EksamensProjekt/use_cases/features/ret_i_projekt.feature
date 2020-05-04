Feature: Ret i projekt
  Description: Ret i projektets data
	Actor: Projekt-Leder
	
Scenario: Projektleder oensker at rette i data for sit projekt
	Given der er et projekt "GammeltNavn" med lederen "Erik"
	And Projektet "GammeltNavn" har aktiviteten "aktivitet2"
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
    Then Projektet har ikke laengere aktiviteten "Udvikling af brugergraenseflade"


Scenario: Projektleder proever at slette en aktivitet som har timer registreret
	Given der er et projekt "Skoleovervaagning" med lederen "KLAU"
    And Projektet "Skoleovervaagning" har aktiviteten "Planlaegning"
    And aktiviteten "Planlaegning" i projektet "Skoleovervaagning" har 5 timer registreret
    When Projektlederen sletter aktiviteten "Planlaegning"
    Then aktiviteten "Planlaegning" er ikke slettet
    

Scenario: Projekt faar aendret sin slutdato saaledes at en medarbejder kommer til at have mere end 20 aktiviteter paa 1 uge
	Given en medarbjeder "TJR" som har 20 aktiviteter i uge 2
	And medarbejderen "TJR" har 1 aktivitet "Debug" uge 1 i projektet "Projekt6"
	When Der forsoeges at Aktiviteten "Debug" i projekt "Projekt6" faar sin slut-uge aendret til uge 2
	Then Aktiviteten er ikke flyttet.
