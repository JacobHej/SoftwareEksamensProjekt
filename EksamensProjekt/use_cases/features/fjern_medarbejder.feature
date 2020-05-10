Feature: Fjern medarbejder fra systemet
  Description: 
	Actor: Medarbejder
	
Scenario: Der er en medarbejder uden planer
Given Der er en medarbejder med navnet "SUNN"
And "SUNN" har 0 planlagte ferier
And "SUNN" har 0 aktiviteter
And "SUNN" har 0 Brugt tid
Then medarbejderen "SUNN" kan slettes

Scenario: Der er en medarbejder uden planer
Given Der er en medarbejder med navnet "SUN"
And "SUN" har 1 planlagte ferier
And "SUN" har 0 aktiviteter
And "SUN" har 0 Brugt tid
Then medarbejderen "SUN" kan ikke slettes

Scenario: Der er en medarbejder uden planer
Given Der er en medarbejder med navnet "SU"
And "SU" har 0 planlagte ferier
And "SU" har 1 aktiviteter
And "Su" har 0 Brugt tid
Then medarbejderen "SU" kan ikke slettes

Scenario: Der er en medarbejder uden planer
Given Der er en medarbejder med navnet "S"
And "S" har 0 planlagte ferier
And "S" har 0 aktiviteter
And "S" har 1 Brugt tid
Then medarbejderen "S" kan ikke slettes