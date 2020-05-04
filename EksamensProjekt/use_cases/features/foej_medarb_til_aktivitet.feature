Feature: Tilfoej en medarbejder til en aktivitet
  Description: Findes hovedsageligt i medarbejdersteps
	Actor: Medarbejder
	
Scenario: medarbejder som er ledig bliver tilfoejet til en aktivitet
	Given medarbejder "ULRI" har mindre end 20 vagter i uge 33
	When medarbejder "ULRI" faar aktiviteten "Rename Files"
	Then medarbejder "ULRI" har faaet aktiviteten "Rename Files"

Scenario: medarbejder som har 20 vagter i en uge bliver tildelt endnu en
	Given medarbjeder "AAOE" som har 20 aktiviteter i uge 2
	When "AAOE" faar endnu en aktivitet i uge 2
	Then "AAOE" har ikke faaet denne aktivitet

#Scenario: medarbejder melder ferie i uge hvor han har 0 aktiviteter
#	Given der er en medarbejder "AASD"
#	And "AASD" har 0 aktiviteter i uge 22
#	When "AASD" melder ferie i uge 22
#	Then "AASD" er optaget af ferie i uge 22
#
#Scenario: medarbejder  melder ferie i uge hvor han har aktiviteter
#	Given der er en medarbejder "CKOE"
#	And "CKOE" har 3 aktiviteter i uge 25
#	When "CKOE" melder ferie i uge 25
#	Then "CKOE" har ikke faaet ferie u uge 25