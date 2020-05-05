Feature: Der oprettes en ny medarbejder
	Description: Der oprettes en ny medarbejder (der checkes for om eksisterende medarbejder har samme navn)
		Actor: Medarbejder
		
Scenario: Der oprettes en ny medarbejder uden problemer
 	Given Der findes ikke en medarbejder med navnet "FISK"
 	Then Der kan oprettes en medarbejder med navnet "FISK"
 	
Scenario: Der forsoeges at oprette en ny medarbejder med eksisterende medarbejder har samme navn
	Given Der er en medarbejder med navnet "HENN"
	Then Der kan ikke oprettes en ny medarbejder med navnet "HENN"