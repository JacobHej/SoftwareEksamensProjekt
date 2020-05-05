Feature: Se medarbejder information
	Description: Der oenskes information om medarbejders information
		Actor: Medarbejder
		
Scenario: Hvilke uger har medarbejder aktiviteter
	Given Der er en medarbejder med navnet "PAUL"
	And "PAUL" har en aktivitet "Vaer PAUL" i uge 4
	And "PAUL" har en aktivitet "Bliv ved med at vaere PAUL" i uge 5
	When Der spoerges om hvornaar "PAUL" har aktiviteter
	Then Svaret er uge 4 og 5
	
Scenario: Hvor mange aktiviteter har medarbejder i given uge
	Given vi kigger igen paa medarbejderen "PAUL"
	And "PAUL" faar endnu en aktivitet "Undgaa at lave andet en at vaere PAUL" i uge 5
	Then det kan ses at "PAUL" har 1 aktivitet i uge 4 og 2 aktiviteter i uge 5
	
Scenario: Hvilke aktiviteter har medarbejderen
	Given vi kigger igen paa medarbejderen "PAUL"
	Then det ses at "PAUL" har aktiviteterme "Vaer PAUL" og "Bliv ved med at vaere PAUL" og "Undgaa at lave andet en at vaere PAUL"
	