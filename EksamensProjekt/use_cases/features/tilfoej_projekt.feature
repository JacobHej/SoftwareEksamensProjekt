Feature: tilfoej projekt
  Description: Lav et nyt projekt
	Actor: Medarbejder


Scenario: Medarbejder opretter nyt projekt
    Given der er en medarbejder med ID'et "MedarbejderID"
    When Medarbejderen opretter et projekt med navnet "ProjektNavn"
    Then Der findes et nyt projekt med navnet "ProjektNavn"
    
    
#Scenario: Medarbejder forsoeger at oprette projekt som allerede findes
#    Given der er en medarbejder med ID'et "MedarbejderID" og et projekt med ID'et "ProjektID"
#    When Medarbejderen opretter et projekt med ID'et "ProjektID"
#    Then Kast exception "Projekt med dette ID findes allerede!"			 		<--- Ved ikke om den skal vaere med, maa 2 projekter have samme navn?
