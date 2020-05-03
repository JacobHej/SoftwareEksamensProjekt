Feature: tilfoej aktivitet
  Description: Lav en aktivitet for et projekt
	Actor: Projekt-Leder

Scenario: Lederen laver en ny aktivitet
    Given der er et projekt "ProjektNavn" med lederen "leder"
    When lederen laver en ny aktivitet med navnet "aktivitetID"
    Then projektet "ProjektNavn" har en ny aktivitet med navnet "aktivitetID"

Scenario: Lederen proever at lave en ny aktivitet som findes
    Given der er et projekt "ProjektNavn" med lederen "leder"
    And Projektet "ProjektNavn" har aktiviteten "aktivitetID"
    When lederen proever at lave en ny aktivitet med navnet "aktivitetID"
    Then Aktiviteten er ikke tilfoejet