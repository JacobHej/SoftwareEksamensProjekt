Feature: tilfoej projekt
  Description: Lav et nyt projekt
	Actor: Medarbejder


Scenario: Medarbejder opretter nyt projekt
    When Medarbejderen opretter et projekt med navnet "Projekt4"
    Then Der findes et nyt projekt med navnet "Projekt4"
    And projektet "Projekt4" har et loebenummer som er tildelt af systemet
    
