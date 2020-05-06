Feature: Lav Rapport over projekt
  Description: Projektlederen laver en rapport over sit projekt
	Actor: Projekt-Leder
	
Scenario: Projektleder opretter rapport over projekt
	Given Der er et projekt "Vilkaarligt projekt" som har projektlederen "SIGG"
	And projektet "Vilkaarligt projekt" har aktiviteten "Vilkaarlig aktivitet 1" 
	And projektet "Vilkaarligt projekt" har aktiviteten "Vilkaarlig aktivitet 2"
	And Projektlederen som ikke er tildelt en aktivitet melder at have arbejdet 4 timer paa "Vilkaarlig aktivitet 1"
	And Der er en medarbejder "FAAV" som er tildelt aktiviteten "Vilkaarlig aktivitet 2"
	And Medarbejderen "FAAV" registrerer 6 timer paa aktiviteten "Vilkaarlig aktivitet 2"
	And Medarbejderen "FAAV" registrerer 9 timer paa aktiviteten "Vilkaarlig aktivitet 1"
	When Der laves en rapport over projektet "Vilkaarligt projekt"
	Then Det fremgaar af rapporten, at der er 2 aktiviteter i projektet med navne "Vilkaarlig aktivitet 1" og "Vilkaarlig aktivitet 2"
	And "Vilkaarlig aktivitet 2" er blevet tildelt medarbejderen "FAAV" og "Vilkaarlig aktivitet 1" har ingen medarbejder tildelt
	And Der er 9 timer registreret på aktiviteten "Vilkaarlig aktivitet 1" og 6 timer paa aktiviteten "Vilkaarlig aktivitet 2"