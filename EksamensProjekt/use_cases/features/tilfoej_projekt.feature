Feature: tilfoej projekt
  Description: Lav et nyt projekt
	Actor: Medarbejder


Scenario: Medarbejder opretter nyt projekt
    When Medarbejderen opretter et projekt med navnet "ProjektNavn"
    Then Der findes et nyt projekt med navnet "ProjektNavn"
    And projektet "ProjektNavn" har et loebenummer som er tildelt af systemet
    
