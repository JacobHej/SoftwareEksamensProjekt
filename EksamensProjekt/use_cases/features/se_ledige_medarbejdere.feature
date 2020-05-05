Feature: Se ledige medarbejdere.
	Description: Se hvilke medarbejdere der er ledige i en given uge
		Actor: Medarbejder
		
Scenario: Det oenskes at vide hvilke medarbejdere der er ledige i Uge 50
	Given Der er en medarbejder "LEDI" som er ledig i uge 50
	And Der er en medarbejder som "IKLD" som ikke er ledig i uge 50
	When Der spoerges om ledige medarbejdere i uge 50
	Then "LEDI" er i listen af ledige medarbejdere
	And "IKLD" er ikke i listen af ledige medarbejdere